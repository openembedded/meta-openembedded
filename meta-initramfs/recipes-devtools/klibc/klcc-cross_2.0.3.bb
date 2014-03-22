SUMMARY = "The klcc crosscompiler for klibc"

require klibc.inc
DEPENDS = "klibc"

FILESPATH =. "${FILE_DIRNAME}/klibc-${PV}:"

SRC_URI += "file://use-env-for-perl.patch"

inherit cross

# disable task already run in klibc recipe
do_configure[noexec] = "1"
do_compile() {
    oe_runmake 'INSTALLDIR=${STAGING_DIR_TARGET}${target_libdir}/klibc' klcc
}
# The linux-libc-headers and klibc custom headers are not machine-specific
# but are installed into machine sysroot.
# Klcc wrapper is hardcoding some of these paths thus, to keep the recipe
# arch-specific, we force the rebuild of klcc-cross for each machine.
do_compile[vardeps] += "MACHINE"
do_install() {
    install -d ${D}${bindir}
    install -m 0755 klcc/klcc ${D}${bindir}/${TARGET_PREFIX}klcc
}


# disable unneeded tasks
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
