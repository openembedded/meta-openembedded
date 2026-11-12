SUMMARY = "pngcheck verifies the integrity of PNG, JNG and MNG files"
HOMEPAGE = "http://www.libpng.org/pub/png/apps/pngcheck.html"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://gpl/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "zlib libpng"

SRC_URI = "http://www.libpng.org/pub/png/src/${BPN}-${PV}.tar.gz \
           file://10-pngsplit-format-strings.patch \
           file://0001-png-fix-IDAT-windowsize-Fix-format-string-errors-in-.patch \
           file://0001-make-Respect-variables-from-environement.patch \
           "

SRC_URI[sha256sum] = "c36a4491634af751f7798ea421321642f9590faa032eccb0dd5fb4533609dee6"

UPSTREAM_CHECK_URI = "http://www.libpng.org/pub/png/apps/pngcheck.html"
UPSTREAM_CHECK_REGEX = "${BPN}-(?P<pver>\d+(\.\d+)+)\.tar"

CFLAGS += "-DUSE_ZLIB"

EXTRA_OEMAKE = "-f ${S}/Makefile.unx"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install pngcheck ${D}${bindir}
    install png-fix-IDAT-windowsize ${D}${bindir}
    install pngsplit ${D}${bindir}
}
