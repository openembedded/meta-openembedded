DESCRIPTION = "Python interface to Bluetooth LE on Linux"
HOMEPAGE = "https://github.com/IanHarvey/bluepy"
SECTION = "devel/python"

DEPENDS = "${PYTHON_PN} glib-2.0"
RDEPENDS_${PN} = "bluez5"

LICENSE = "GPLv2 & PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"

inherit setuptools3 pkgconfig

SRC_URI = "git://github.com/IanHarvey/bluepy.git;protocol=https;rev=7ad565231a97c304c0eff45f2649cd005e69db09"
S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"
