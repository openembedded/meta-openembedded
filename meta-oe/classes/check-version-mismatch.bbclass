inherit qemu

ENABLE_VERSION_MISMATCH_CHECK ?= "${@'1' if bb.utils.contains('MACHINE_FEATURES', 'qemu-usermode', True, False, d) else '0'}"
DEBUG_VERSION_MISMATCH_CHECK ?= "1"
CHECK_VERSION_PV ?= ""

DEPENDS:append:class-target = "${@' qemu-native' if bb.utils.to_boolean(d.getVar('ENABLE_VERSION_MISMATCH_CHECK')) else ''}"

QEMU_EXEC ?= "${@qemu_wrapper_cmdline(d, '${STAGING_DIR_HOST}', ['${STAGING_DIR_HOST}${libdir}','${STAGING_DIR_HOST}${base_libdir}', '${PKGD}${libdir}', '${PKGD}${base_libdir}'])}"

python do_package_check_version_mismatch() {
    import re
    import subprocess
    import shutil
    import signal

    classes_skip = ["nopackage", "image", "native", "cross", "crosssdk", "cross-canadian"]
    for cs in classes_skip:
        if bb.data.inherits_class(cs, d):
            bb.note(f"Skip do_package_check_version_mismatch as {cs} is inherited.")
            return

    if not bb.utils.to_boolean(d.getVar('ENABLE_VERSION_MISMATCH_CHECK')):
        bb.note("Skip do_package_check_version_mismatch as ENABLE_VERSION_MISMATCH_CHECK is disabled.")
        return

    __regexp_version_broad_match__ = re.compile(r"(?:\s|^|-|_|/|=| go|\()" +
                                                r"(?P<version>v?[0-9][0-9.][0-9+.\-_~\(\)]*?|UNKNOWN)" +
                                                r"(?:[+\-]release.*|[+\-]stable.*|)" +
                                                r"(?P<extra>[+\-]unknown|[+\-]dirty|[+\-]rc?\d{1,3}|\+cargo-[0-9.]+|" +
                                                r"[a-z]|-?[pP][0-9]{1,3}|-?beta[^\s]*|-?alpha[^\s]*|)" +
                                                r"(?P<extra2>[+\-]dev|[+\-]devel|)" +
                                                r"(?:,|:|\.|\)|-[0-9a-g]{6,42}|)" +
                                                r"(?=\s|$)"
                                                )
    __regexp_exclude_year__ = re.compile(r"^(19|20)[0-9]{2}$")
    __regexp_single_number_ending_with_dot__ = re.compile(r"^\d\.$")

    def is_shared_library(filepath):
        return re.match(r'.*\.so(\.\d+)*$', filepath) is not None

    def get_possible_versions(output_contents, full_cmd=None, max_lines=None):
        #
        # Algorithm:
        #   1. Check version line by line.
        #   2. Skip some lines which we know that do not contain version information, e.g., License, Copyright.
        #   3. Do broad match, finding all possible versions.
        #   4. If there's a version found by any match, do exclude match (e.g., exclude years)
        #   5. If there's a valid version, do stripping and converting and then add to possible_versions.
        #   6. Return possible_versions
        #
        possible_versions = []
        content_lines = output_contents.split("\n")
        if max_lines:
            content_lines = content_lines[0:max_lines]
        if full_cmd:
            base_cmd = os.path.basename(full_cmd)
        __regex_help_format__ = re.compile(r"-[^\s].*")
        for line in content_lines:
            line = line.strip()
            # skip help lines
            if __regex_help_format__.match(line):
                continue
            # avoid command itself affecting output
            if full_cmd:
                if line.startswith(base_cmd):
                    line = line[len(base_cmd):]
                elif line.startswith(full_cmd):
                    line = line[len(full_cmd):]
            # skip specific lines
            skip_keywords_start = ["copyright", "license", "compiled", "build", "built"]
            skip_line = False
            for sks in skip_keywords_start:
                if line.lower().startswith(sks):
                    skip_line = True
                    break
            if skip_line:
                continue

            # try broad match
            for match in __regexp_version_broad_match__.finditer(line):
                version = match.group("version")
                #print(f"version = {version}")
                # do exclude match
                exclude_match = __regexp_exclude_year__.match(version)
                if exclude_match:
                    continue
                exclude_match = __regexp_single_number_ending_with_dot__.match(version)
                if exclude_match:
                    continue
                # do some stripping and converting
                if version.startswith("("):
                    version = version[1:-1]
                if version.startswith("v"):
                    version = version[1:]
                if version.endswith(")") and "(" not in version:
                    version = version[:-1]
                # handle extra version info
                version = version + match.group("extra") + match.group("extra2")
                possible_versions.append(version)
        return possible_versions

    def is_version_mismatch(rvs, pv):
        got_match = False
        if pv.startswith("git"):
            return False
        if "-pre" in pv:
            pv = pv.split("-pre")[0]
        if pv.startswith("v"):
            pv = pv[1:]
        for rv in rvs:
            if rv == pv:
                got_match = True
                break
            pv = pv.split("+git")[0]
            # handle % character in pv which means matching any chars
            if '%' in pv:
                escaped_pv = re.escape(pv)
                regex_pattern = escaped_pv.replace('%', '.*')
                regex_pattern = f'^{regex_pattern}$'
                if re.fullmatch(regex_pattern, rv):
                    got_match = True
                    break
                else:
                    continue
            # handle cases such as 2.36.0-r0 v.s. 2.36.0
            if "-r" in rv:
                rv = rv.split("-r")[0]
            chars_to_replace = ["-", "+", "_", "~"]
            # convert to use "." as the version seperator
            for cr in chars_to_replace:
                rv = rv.replace(cr, ".")
                pv = pv.replace(cr, ".")
            if rv == pv:
                got_match = True
                break
            # handle case such as 5.2.37(1) v.s. 5.2.37
            if "(" in rv:
                rv = rv.split("(")[0]
                if rv == pv:
                    got_match = True
                    break
            # handle case such as 4.4.3p1
            if "p" in pv and "p" in rv.lower():
                pv = pv.lower().replace(".p", "p")
                rv = rv.lower().replace(".p", "p")
                if pv == rv:
                    got_match = True
                    break
            # handle cases such as 6.00 v.s. 6.0
            if rv.startswith(pv):
                if rv == pv + "0" or rv == pv + ".0":
                    got_match = True
                    break
            elif pv.startswith(rv):
                if pv == rv + "0" or pv == rv + ".0":
                    got_match = True
                    break
            # handle cases such as 21306 v.s. 2.13.6
            if "." in pv and not "." in rv:
                pv_components = pv.split(".")
                if rv.startswith(pv_components[0]):
                    pv_num = 0
                    for i in range(0, len(pv_components)):
                        pv_num = pv_num * 100 + int(pv_components[i])
                    if pv_num == int(rv):
                        got_match = True
                        break
        if got_match:
            return False
        else:
            return True

    # helper function to get PKGV, useful for recipes such as perf
    def get_pkgv(pn):
        pkgdestwork = d.getVar("PKGDESTWORK")
        recipe_data_fn = pkgdestwork + "/" + pn
        pn_data = oe.packagedata.read_pkgdatafile(recipe_data_fn)
        if not "PACKAGES" in pn_data:
            return d.getVar("PV")
        packages = pn_data["PACKAGES"].split()
        for pkg in packages:
            pkg_fn = pkgdestwork + "/runtime/" + pkg
            pkg_data = oe.packagedata.read_pkgdatafile(pkg_fn)
            if "PKGV" in pkg_data:
                return pkg_data["PKGV"]

    #
    # traverse PKGD, find executables and run them to get runtime version information and compare it with recipe version information
    #
    enable_debug = bb.utils.to_boolean(d.getVar("DEBUG_VERSION_MISMATCH_CHECK"))
    pkgd = d.getVar("PKGD")
    pn = d.getVar("PN")
    pv = d.getVar("CHECK_VERSION_PV")
    if not pv:
        pv = get_pkgv(pn)
    qemu_exec = d.getVar("QEMU_EXEC").strip()
    executables = []
    possible_versions_all = []
    data_lines = []

    if enable_debug:
        debug_directory = d.getVar("TMPDIR") + "/check-version-mismatch"
        debug_data_file = debug_directory + "/" + pn
        os.makedirs(debug_directory, exist_ok=True)
        data_lines.append("pv: %s\n" % pv)

    got_quick_match_result = False
    # handle python3-xxx recipes quickly
    __regex_python_module_version__ = re.compile(r"(?:^|.*:)Version: (?P<version>.*)$")
    if "python3-" in pn:
        version_check_cmd = "find %s -name 'METADATA' | xargs grep '^Version: '" % pkgd
        try:
            output = subprocess.check_output(version_check_cmd, shell=True).decode("utf-8")
            data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
            data_lines.append("output:\n'''\n%s'''\n" % output)
            possible_versions = []
            for line in output.split("\n"):
                match = __regex_python_module_version__.match(line)
                if match:
                    possible_versions.append(match.group("version"))
            possible_versions = sorted(set(possible_versions))
            data_lines.append("possible versions: %s\n" % possible_versions)
            if is_version_mismatch(possible_versions, pv):
                data_lines.append("FINAL RESULT: MISMATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
                bb.warn("Possible runtime versions %s do not match recipe version %s" % (possible_versions, pv))
            else:
                data_lines.append("FINAL RESULT: MATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
            got_quick_match_result = True
        except:
            data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
            data_lines.append("result: RUN_FAILED\n\n")
    if got_quick_match_result:
        if enable_debug:
            with open(debug_data_file, "w") as f:
                f.writelines(data_lines)
        return

    # handle .pc files
    version_check_cmd = "find %s -name '*.pc' | xargs grep -i version" % pkgd
    try:
        output = subprocess.check_output(version_check_cmd, shell=True).decode("utf-8")
        data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
        data_lines.append("output:\n'''\n%s'''\n" % output)
        possible_versions = get_possible_versions(output)
        possible_versions = sorted(set(possible_versions))
        data_lines.append("possible versions: %s\n" % possible_versions)
        if is_version_mismatch(possible_versions, pv):
            if pn.startswith("lib"):
                data_lines.append("FINAL RESULT: MISMATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
                bb.warn("Possible runtime versions %s do not match recipe version %s" % (possible_versions, pv))
                got_quick_match_result = True
            else:
                data_lines.append("result: MISMATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
        else:
            data_lines.append("FINAL RESULT: MATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
            got_quick_match_result = True
    except:
        data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
        data_lines.append("result: RUN_FAILED\n\n")
    if got_quick_match_result:
        if enable_debug:
            with open(debug_data_file, "w") as f:
                f.writelines(data_lines)
        return

    skipped_directories = [".debug", "ptest", "installed-tests", "tests", "test", "__pycache__", "testcases"]
    pkgd_libdir = pkgd + d.getVar("libdir")
    pkgd_base_libdir = pkgd + d.getVar("base_libdir")
    extra_exec_libdirs = []
    for root, dirs, files in os.walk(pkgd):
        for dname in dirs:
            fdir = os.path.join(root, dname)
            if os.path.isdir(fdir) and fdir != pkgd_libdir and fdir != pkgd_base_libdir:
                if fdir.startswith(pkgd_libdir) or fdir.startswith(pkgd_base_libdir):
                    for sd in skipped_directories:
                        if fdir.endswith("/" + sd) or ("/" + sd + "/") in fdir:
                            break
                    else:
                        extra_exec_libdirs.append(fdir)
        for fname in files:
            fpath = os.path.join(root, fname)
            if os.path.isfile(fpath) and os.access(fpath, os.X_OK):
                for sd in skipped_directories:
                    if ("/" + sd + "/") in fpath:
                        break
                else:
                    if is_shared_library(fpath):
                        # we don't check shared libraries
                        continue
                    else:
                        executables.append(fpath)
    if enable_debug:
        data_lines.append("executables: %s\n" % executables)

    found_match = False
    some_cmd_succeed = False
    if not executables:
        bb.debug(1, "No executable found for %s" % pn)
        data_lines.append("FINAL RESULT: NO_EXECUTABLE_FOUND\n\n")
    else:
        # first we extend qemu_exec to include library path if needed
        if extra_exec_libdirs:
            qemu_exec += ":" + ":".join(extra_exec_libdirs)
        for fexec in executables:
            for version_option in ["--version", "-V", "-v", "--help"]:
                version_check_cmd_full = "%s %s %s" % (qemu_exec, fexec, version_option)
                version_check_cmd = version_check_cmd_full
                #version_check_cmd = "%s %s" % (os.path.relpath(fexec, pkgd), version_option)
                
                try:
                    cwd_temp = d.getVar("TMPDIR") + "/check-version-mismatch/cwd-temp/" + pn
                    os.makedirs(cwd_temp, exist_ok=True)
                    # avoid pseudo to manage any file we create
                    sp_env = os.environ.copy()
                    sp_env["PSEUDO_UNLOAD"] = "1"
                    output = subprocess.check_output(version_check_cmd_full,
                                                     shell=True,
                                                     stderr=subprocess.STDOUT,
                                                     cwd=cwd_temp,
                                                     timeout=10,
                                                     env=sp_env).decode("utf-8")
                    some_cmd_succeed = True
                    data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
                    data_lines.append("output:\n'''\n%s'''\n" % output)
                    if version_option == "--help":
                        max_lines = 5
                    else:
                        max_lines = None
                    possible_versions = get_possible_versions(output, full_cmd=fexec, max_lines=max_lines)
                    if "." in pv:
                        possible_versions = [item for item in possible_versions if "." in item or item == "UNKNOWN"]
                    data_lines.append("possible versions: %s\n" % possible_versions)
                    if not possible_versions:
                        data_lines.append("result: NO_RUNTIME_VERSION_FOUND\n\n")
                        continue
                    possible_versions_all.extend(possible_versions)
                    possible_versions_all = sorted(set(possible_versions_all))
                    if is_version_mismatch(possible_versions, pv):
                        data_lines.append("result: MISMATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
                    else:
                        found_match = True
                        data_lines.append("result: MATCH (%s v.s. %s)\n\n" % (possible_versions, pv))
                        break
                except:
                    data_lines.append("version_check_cmd: %s\n" % version_check_cmd)
                    data_lines.append("result: RUN_FAILED\n\n")
                finally:
                    shutil.rmtree(cwd_temp)
            if found_match:
                break
    if executables:
        if found_match:
            data_lines.append("FINAL RESULT: MATCH (%s v.s. %s)\n" % (possible_versions_all, pv))
        elif len(possible_versions_all) == 0:
            if some_cmd_succeed:
                bb.debug(1, "No valid runtime version found")
                data_lines.append("FINAL RESULT: NO_VALID_RUNTIME_VERSION_FOUND\n")
            else:
                bb.debug(1, "All version check command failed")
                data_lines.append("FINAL RESULT: RUN_FAILED\n")
        else:
            bb.warn("Possible runtime versions %s do not match recipe version %s" % (possible_versions_all, pv))
            data_lines.append("FINAL RESULT: MISMATCH (%s v.s. %s)\n" % (possible_versions_all, pv))

    if enable_debug:
        with open(debug_data_file, "w") as f:
            f.writelines(data_lines)

    # clean up stale processes
    process_name_common_prefix = "%s %s" % (' '.join(qemu_exec.split()[1:]), pkgd)
    find_stale_process_cmd = "ps -e -o pid,args | grep -v grep | grep -F '%s'" % process_name_common_prefix
    try:
        stale_process_output = subprocess.check_output(find_stale_process_cmd, shell=True).decode("utf-8")
        stale_process_pids = []
        for line in stale_process_output.split("\n"):
            line = line.strip()
            if not line:
                continue
            pid = line.split()[0]
            stale_process_pids.append(pid)
        for pid in stale_process_pids:
            os.kill(int(pid), signal.SIGKILL)
    except Exception as e:
        bb.debug(1, "No stale process")
}

addtask do_package_check_version_mismatch after do_package before do_build

do_build[rdeptask] += "do_package_check_version_mismatch"
do_rootfs[recrdeptask] += "do_package_check_version_mismatch"

SSTATETASKS += "do_package_check_version_mismatch"
do_package_check_version_mismatch[sstate-inputdirs] = ""
do_package_check_version_mismatch[sstate-outputdirs] = ""
python do_package_check_version_mismatch_setscene () {
    sstate_setscene(d)
}
addtask do_package_check_version_mismatch_setscene
