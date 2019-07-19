SUMMARY = "Zstandard - Fast real-time compression algorithm"
DESCRIPTION = "Zstandard is a fast lossless compression algorithm, targeting \
real-time compression scenarios at zlib-level and better compression ratios. \
It's backed by a very fast entropy stage, provided by Huff0 and FSE library."
HOMEPAGE = "http://www.zstd.net/"
SECTION = "console/utils"

LICENSE = "BSD-3-Clause & GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c7f0b161edbe52f5f345a3d1311d0b32 \
                    file://COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://github.com/facebook/zstd.git;nobranch=1"

SRCREV = "83b51e9f886be7c2a4d477b6e7bc6db831791d8d"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake
    oe_runmake -C programs zstd-compress zstd-decompress zstd-frugal
}

do_install () {
    oe_runmake install 'DESTDIR=${D}'
    install -d -m 0755 ${D}${bindir}
    install -m 0755 ${S}/programs/zstd-compress ${D}${bindir}
    install -m 0755 ${S}/programs/zstd-decompress ${D}${bindir}
    install -m 0755 ${S}/programs/zstd-frugal ${D}${bindir}
}

PACKAGE_BEFORE_PN += " \
    ${PN}-compress \
    ${PN}-decompress \
    ${PN}-frugal \
"

FILES_${PN}-compress = " \
    ${bindir}/zstd-compress \
"

FILES_${PN}-decompress = " \
    ${bindir}/zstd-decompress \
"

FILES_${PN}-frugal = " \
    ${bindir}/zstd-frugal \
"

BBCLASSEXTEND = "native nativesdk"
