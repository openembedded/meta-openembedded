DESCRIPTION = "A file format designed for highly efficient deltas while maintaining good compression"
AUTHOR = "Jonathan Dieter"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=daf6e68539f564601a5a5869c31e5242"

SRC_URI = "git://github.com/zchunk/zchunk.git;protocol=https;branch=main \
           file://0001-Handle-overflow-errors-in-malformed-zchunk-files.patch \
           file://run-ptest \
           "

SRCREV = "dd6a30a1e4e8b738b0cafc682f3c00e7706134e5"
S = "${WORKDIR}/git"

DEPENDS = "\
    curl \
    zstd \
    "

DEPENDS:append:libc-musl = " argp-standalone"
LDFLAGS:append:libc-musl = " -largp"

inherit meson pkgconfig ptest

do_install_ptest(){
    install -d ${D}${PTEST_PATH}/tests
    find ${B}/test -maxdepth 1 -type f -exec install {} ${D}${PTEST_PATH}/tests/ \;
    cp -r ${S}/test/files ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native nativesdk"

