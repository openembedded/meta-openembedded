PR = "${INC_PR}.0"

require klibc.inc

export KLCC_INST = "${STAGING_DIR_TARGET}/lib/klibc"

SRC_URI += "file://klcc_prefix.patch \
            file://use-env-for-perl.patch"

DEPENDS = "klibc"

FILESPATH =. "${FILE_DIRNAME}/klibc-${PV}:"

# ${TARGET_PREFIX}klcc is just a
# perl wrapper around gcc-cross
# so give it the same arch and path
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit cross

do_configure () {
        :
}

do_compile() {
        oe_runmake klcc
}

do_install() {
        install -d ${D}${base_bindir}
        install -m 0755 klcc/klcc ${D}${base_bindir}/${TARGET_PREFIX}klcc
}
