DESCRIPTION = "Universal Hardware Driver for Ettus Research products."
HOMEPAGE = "http://www.ettus.com"
LICENSE = "GPLV3+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=51b35d652c070d136bf20244494be2d3"

DEPENDS = "git-native boost libusb1 uhd-firmware orc-native"
RDEPENDS_${PN} += "uhd-firmware"

PE = "1"
INC_PR = "r1"

inherit cmake

PACKAGES =+ "${PN}-tests ${PN}-examples ${PN}-e1xx"

INSANE_SKIP_${PN} = "dev-so"

FILES_${PN} += "${libdir}/libuhd.so \
                ${datadir}/uhd/utils/*"
FILES_${PN}-tests += "${datadir}/uhd/tests/*"
FILES_${PN}-e1xx += "${datadir}/uhd/usrp_e_utils/*"
FILES_${PN}-dbg += "${datadir}/uhd/examples/.debug/* \
"                   ${datadir}/uhd/utils/.debug/* \
                    ${datadir}/uhd/tests/.debug/* \
                    ${datadir}/uhd/usrp_e_utils/.debug/*"
FILES_${PN}-examples = "${datadir}/uhd/examples/*"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DENABLE_E100=TRUE -DENABLE_USRP_E_UTILS=TRUE"

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"
