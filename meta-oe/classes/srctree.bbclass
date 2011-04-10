# Copyright (C) 2009 Chris Larson <clarson@kergoth.com>
# Released under the MIT license (see COPYING.MIT for the terms)
#
# srctree.bbclass enables operation inside of an existing source tree for a
# project, rather than using the fetch/unpack/patch idiom.
#
# By default, it expects that you're keeping the recipe(s) inside the
# aforementioned source tree, but you could override S to point at an external
# directory and place the recipes in a normal collection/overlay, if you so
# chose.
#
# It also provides some convenience python functions for assembling your
# do_clean, if you want to leverage things like 'git clean' to simplify the
# operation.


# Grab convenience methods & sane default for do_clean
inherit clean

# Build here
S = "${FILE_DIRNAME}"
SRC_URI = ""

def remove_tasks(deltasks, d):
    for task in filter(lambda k: d.getVarFlag(k, "task"), d.keys()):
        deps = d.getVarFlag(task, "deps")
        for preptask in deltasks:
            if preptask in deps:
                deps.remove(preptask)
        d.setVarFlag(task, "deps", deps)

addtask configure after do_setscene

def merge_tasks(d):
    """
    Merges all of the operations that occur prior to do_populate_sysroot
    into do_populate_sysroot.

    This is necessary because of recipe variants (normal, native, cross,
    sdk).  If a bitbake run happens to want to build more than one of
    these variants in a single run, it's possible for them to step on one
    another's toes, due to the shared ${S}.  Interleaved
    configure/compile/install amongst variants will break things badly.
    """
    from itertools import chain
    from bb import note

    def __gather_taskdeps(task, seen):
        for dep in d.getVarFlag(task, "deps"):
            if not dep in seen:
                __gather_taskdeps(dep, seen)
        if not task in seen:
            seen.append(task)

    def gather_taskdeps(task):
        items = []
        __gather_taskdeps(task, items)
        return items

    newtask = "do_populate_sysroot_post"
    mergedtasks = gather_taskdeps(newtask)
    mergedtasks.pop()

    for task in (key for key in d.keys()
                 if d.getVarFlag(key, "task") and
                 not key in mergedtasks):
        deps = d.getVarFlag(task, "deps")
        for mergetask in mergedtasks:
            if mergetask in (d.getVarFlag(task, "recrdeptask"),
                             d.getVarFlag(task, "recdeptask"),
                             d.getVarFlag(task, "deptask")):
                continue

            if mergetask in deps:
                deps.remove(mergetask)
                #note("removing dep on %s from %s" % (mergetask, task))

                if not newtask in deps:
                    #note("adding dep on %s to %s" % (newtask, task))
                    deps.append(newtask)
        d.setVarFlag(task, "deps", deps)

    # Pull cross recipe task deps over
    depends = []
    deptask = []
    for task in mergedtasks[:-1]:
        depends.append(d.getVarFlag(task, "depends") or "")
        deptask.append(d.getVarFlag(task, "deptask") or "")

    d.setVarFlag("do_populate_sysroot_post", "depends", " ".join(depends))
    d.setVarFlag("do_populate_sysroot_post", "deptask", " ".join(deptask))

python () {
    remove_tasks(["do_patch", "do_unpack", "do_fetch"], d)
    b = d.getVar("B", True)
    if not b or b == d.getVar("S", True):
        merge_tasks(d)
}

# Manually run do_install & all of its deps
python do_populate_sysroot_post () {
    from os.path import exists
    from bb.build import exec_func, make_stamp
    from bb import note

    stamp = d.getVar("STAMP", True)

    def rec_exec_task(task, seen):
        for dep in d.getVarFlag(task, "deps"):
            if not dep in seen:
                rec_exec_task(dep, seen)
        seen.add(task)
        if not exists("%s.%s" % (stamp, task)):
            note("%s: executing task %s" % (d.getVar("PF", True), task))
            exec_func(task, d)
            flags = d.getVarFlags(task)
            if not flags.get('nostamp') and not flags.get('selfstamp'):
                make_stamp(task, d)

    rec_exec_task("do_populate_sysroot", set())
}
addtask populate_sysroot_post after do_populate_sysroot
do_populate_sysroot_post[lockfiles] += "${S}/.lock"
