# gitpkgv.bbclass provides a GITPKGV and GITPKGVTAG variables to be
# used in PKGV, as described bellow:
#
# - GITPKGV which is a sortable version with the format NN+GITHASH, to
#   be used in PKGV, where
#
#   NN equals the total number of revs up to SRCREV
#   GITHASH is SRCREV's (full) hash
#
# - GITPKGVTAG which is the output of 'git describe' allowing for
#   automatic versioning
#
# gitpkgv.bbclass assumes the git repository has been cloned, and
# contains SRCREV. So ${GITPKGV} and ${GITPKGVTAG} should never be
# used in PV, only in PKGV.  It can handle SRCREV = ${AUTOREV}, as
# well as SRCREV = "<some fixed git hash>".
#
# WARNING: if upstream repository is always using consistent and
# sortable tag name scheme you can get sortable version including tag
# name with ${GITPKGVTAG}, but be aware that ie tag sequence "v1.0,
# v1.2, xtest, v2.0" will force you to increment PE to get upgradeable
# path to v2.0 revisions
#
# use example:
#
# inherit gitpkgv
#
# PV = "1.0+gitr${SRCPV}"      # expands to something like 1.0+gitr3+4c1c21d7dbbf93b0df336994524313dfe0d4963b
# PKGV = "1.0+gitr${GITPKGV}"  # expands also to something like 1.0+gitr31337+4c1c21d7d
#
# or
#
# inherit gitpkgv
#
# PV = "1.0+gitr${SRCPV}" # expands to something like 1.0+gitr3+4c1c21d7dbbf93b0df336994524313dfe0d4963b
# PKGV = "${GITPKGVTAG}"  # expands to something like 1.0-31337+g4c1c21d
#                           if there is tag v1.0 before this revision or
#                           ver1.0-31337+g4c1c21d if there is tag ver1.0

GITPKGV = "${@get_git_pkgv(d, False)}"
GITPKGVTAG = "${@get_git_pkgv(d, True)}"

def gitpkgv_drop_tag_prefix(version):
    import re
    if re.match("v\d", version):
        return version[1:]
    else:
        return version

def get_git_pkgv(d, use_tags):
    import os
    import bb

    src_uri = bb.data.getVar('SRC_URI', d, 1).split()
    fetcher = bb.fetch2.Fetch(src_uri, d)
    ud = fetcher.ud

    #
    # If SRCREV_FORMAT is set respect it for tags
    #
    format = bb.data.getVar('SRCREV_FORMAT', d, True)
    if not format:
        format = 'default'

    found = False
    for url in ud.values():
        if url.type == 'git':
            for name, rev in url.revisions.items():
                if not os.path.exists(url.localpath):
                    return None

                found = True

                cwd = os.getcwd()
                os.chdir(url.localpath)

                commits = bb.fetch2.runfetchcmd("git rev-list %s -- 2> /dev/null | wc -l" % rev, d, quiet=True).strip()

                if use_tags:
                    try:
                        output = bb.fetch2.runfetchcmd("git describe %s 2>/dev/null" % rev, d, quiet=True).strip()
                        ver = gitpkgv_drop_tag_prefix(output)
                    except Exception:
                        ver = "0.0-%s-g%s" % (commits, rev[:7])
                else:
                    ver = "%s+%s" % (commits, rev[:7])

                os.chdir(cwd)

                format = format.replace(name, ver)

    if found:
        return format

    return '0+0'
