SUMMARY = "Library for interfacing with IIO devices"
HOMEPAGE = "https://wiki.analog.com/resources/tools-software/linux-software/libiio"
SECTION = "libs"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c"

SRCREV = "e0fc3845506ccb19ab02e4b1d81cddeeee0805ab"
PV = "0.7+git${SRCPV}"

SRC_URI = "git://github.com/analogdevicesinc/libiio.git"

S = "${WORKDIR}/git"

DEPENDS = "flex-native bison-native avahi libaio libusb1 libxml2"

inherit cmake pythonnative

PACKAGES =+ "${PN}-iiod ${PN}-tests ${PN}-python"

RDEPENDS_${PN}-python = "${PN} python-ctypes python-stringold"

FILES_${PN}-iiod = "${sbindir}/iiod"
FILES_${PN}-tests = "${bindir}"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
