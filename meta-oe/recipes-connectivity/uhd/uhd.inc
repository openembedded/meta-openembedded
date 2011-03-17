DESCRIPTION = "Universal Hardware Driver for Ettus Research products."
HOMEPAGE = "http://www.ettus.com"
LICENSE = "GPLV3+"

DEPENDS = "git-native python-cheetah-native boost libusb1 uhd-firmware"
RDEPENDS_${PN} += "uhd-firmware"

INC_PR = "r4"

inherit cmake

PACKAGES =+ "${PN}-tests ${PN}-examples"

FILES_${PN} += "${libdir}/libuhd.so"
FILES_${PN} += "${datadir}/uhd/utils/*"
FILES_${PN}-tests += "${datadir}/uhd/tests/*"
FILES_${PN}-dbg += "${datadir}/uhd/examples/.debug/*"
FILES_${PN}-dbg += "${datadir}/uhd/utils/.debug/*"
FILES_${PN}-dbg += "${datadir}/uhd/tests/.debug/*"
FILES_${PN}-examples = "${datadir}/uhd/examples/*"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DENABLE_USRP_E100=TRUE"

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"
