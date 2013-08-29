PR = "${INC_PR}.0"

require klibc.inc
SUMMARY = "The klcc crosscompiler for klibc"

SRC_URI += "file://use-env-for-perl.patch"

DEPENDS = "klibc"

FILESPATH =. "${FILE_DIRNAME}/klibc-${PV}:"

inherit cross

do_configure () {
    :
}

do_compile() {
    oe_runmake 'INSTALLDIR=${STAGING_DIR_TARGET}${target_libdir}/klibc' klcc
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 klcc/klcc ${D}${bindir}/${TARGET_PREFIX}klcc
}
