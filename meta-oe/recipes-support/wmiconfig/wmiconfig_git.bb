SUMMARY = "Atheros 6K Wifi configuration utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://wmiconfig.c;endline=19;md5=4394a56bca1c5b2446c9f8e406c82911"
SECTION = "console/network"
PV = "1.0+git${SRCPV}"

SRCREV = "0bde889e6fc09a330d0e0b9eb9808b20b2bf13d1"
SRC_URI = "git://github.com/openmoko/openmoko-svn.git;protocol=https;subpath=src/target/AR6kSDK.build_sw.18 \
    file://0001-makefile-Pass-CFLAGS-to-compile.patch \
    file://0002-fix-err-API-to-have-format-string.patch \
"
S = "${WORKDIR}/AR6kSDK.build_sw.18/host/tools/wmiconfig"

CLEANBROKEN = "1"

EXTRA_OEMAKE = "-e MAKEFLAGS="

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 wmiconfig ${D}${bindir}
}

