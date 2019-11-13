SUMMARY = "Kernel selftest for Linux"
DESCRIPTION = "Kernel selftest for Linux"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://../COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS = "rsync-native llvm-native"

# for musl libc
SRC_URI_append_libc-musl = "\
                      file://userfaultfd.patch \
                      "
SRC_URI += "file://run-ptest \
            file://COPYING \
            "

# now we just test bpf and vm
# we will append other kernel selftest in the future
# bpf was added in 4.10 with: https://github.com/torvalds/linux/commit/5aa5bd14c5f8660c64ceedf14a549781be47e53d
# if you have older kernel than that you need to remove it from PACKAGECONFIG
PACKAGECONFIG ??= "bpf vm"
PACKAGECONFIG_remove_x86 = "bpf"
PACKAGECONFIG_remove_arm = "bpf"

PACKAGECONFIG[bpf] = ",,elfutils libcap libcap-ng rsync-native,"
PACKAGECONFIG[vm] = ",,libcap,libgcc bash"

do_patch[depends] += "virtual/kernel:do_shared_workdir"

inherit linux-kernel-base kernel-arch ptest

S = "${WORKDIR}/${BP}"

TEST_LIST = "\
    ${@bb.utils.filter('PACKAGECONFIG', 'bpf vm', d)} \
"

EXTRA_OEMAKE = '\
    CROSS_COMPILE=${TARGET_PREFIX} \
    ARCH=${ARCH} \
    CC="${CC}" \
    AR="${AR}" \
    LD="${LD}" \
    DESTDIR="${D}" \
'

KERNEL_SELFTEST_SRC ?= "Makefile \
                        include \
                        tools \
                        scripts \
                        arch \
                        LICENSES \
"

python __anonymous () {
    import re

    var = d.getVar('TARGET_CC_ARCH')
    pattern = '_FORTIFY_SOURCE=[^0]'

    if re.search(pattern, var):
        d.appendVar('TARGET_CC_ARCH', " -O")
}

do_compile() {
    if [ ${@bb.utils.contains('DEPENDS', 'clang-native', 'True', 'False', d)} = 'False' ]; then
        bbwarn "clang >= 6.0 with bpf support is needed with kernel 4.18+ so
either install it and add it to HOSTTOOLS, or add clang-native from meta-clang to dependency"
    fi
    for i in ${TEST_LIST}
    do
        oe_runmake -C ${S}/tools/testing/selftests/${i}
    done
}

do_install() {
    for i in ${TEST_LIST}
    do
        oe_runmake -C ${S}/tools/testing/selftests/${i} INSTALL_PATH=${D}/usr/kernel-selftest/${i} install
    done
    if [ -e ${D}/usr/kernel-selftest/bpf/test_offload.py ]; then
	sed -i -e '1s,#!.*python3,#! /usr/bin/env python3,' ${D}/usr/kernel-selftest/bpf/test_offload.py
    fi
    chown root:root  -R ${D}/usr/kernel-selftest
}

do_configure() {
    install -D -m 0644 ${WORKDIR}/COPYING ${S}/COPYING
}

do_patch[prefuncs] += "copy_kselftest_source_from_kernel remove_unrelated"
python copy_kselftest_source_from_kernel() {
    sources = (d.getVar("KERNEL_SELFTEST_SRC") or "").split()
    src_dir = d.getVar("STAGING_KERNEL_DIR")
    dest_dir = d.getVar("S")
    bb.utils.mkdirhier(dest_dir)
    for s in sources:
        src = oe.path.join(src_dir, s)
        dest = oe.path.join(dest_dir, s)
        if os.path.isdir(src):
            oe.path.copytree(src, dest)
        else:
            bb.utils.copyfile(src, dest)
}

remove_unrelated() {
    if ${@bb.utils.contains('PACKAGECONFIG','bpf','true','false',d)} ; then
        test -f ${S}/tools/testing/selftests/bpf/Makefile && \
            sed -i -e 's/test_pkt_access.*$/\\/' \
                   -e 's/test_pkt_md_access.*$/\\/' \
                   -e 's/sockmap_verdict_prog.*$/\\/' \
                   ${S}/tools/testing/selftests/bpf/Makefile || \
            bberror "Your kernel is probably older than 4.10 and doesn't have tools/testing/selftests/bpf/Makefile file from https://github.com/torvalds/linux/commit/5aa5bd14c5f8660c64ceedf14a549781be47e53d, disable bpf PACKAGECONFIG"
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_PACKAGE_DEBUG_SPLIT="1"
FILES_${PN} += "/usr/kernel-selftest"

RDEPENDS_${PN} += "python3"
# tools/testing/selftests/vm/Makefile doesn't respect LDFLAGS and tools/testing/selftests/Makefile explicitly overrides to empty
INSANE_SKIP_${PN} += "ldflags"

# userfaultfd.c:126:2: error: format not a string literal and no format arguments [-Werror=format-security]
#  fprintf(stderr, examples);
#  ^~~~~~~
SECURITY_STRINGFORMAT = ""

# https://errors.yoctoproject.org/Errors/Details/261657/
# kernel-selftest/1.0-r0/recipe-sysroot/usr/include/bits/fcntl2.h:50:4: error: call to '__open_missing_mode' declared with attribute error: open with O_CREAT or O_TMPFILE in second argument needs 3 arguments
#     __open_missing_mode ();
#     ^~~~~~~~~~~~~~~~~~~~~~
lcl_maybe_fortify = ""
