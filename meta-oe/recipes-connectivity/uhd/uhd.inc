DESCRIPTION = "Universal Hardware Driver for Ettus Research products."
HOMEPAGE = "http://www.ettus.com"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=51b35d652c070d136bf20244494be2d3"

DEPENDS = "git-native python-cheetah-native boost libusb1 uhd-firmware orc-native ncurses"
RDEPENDS_${PN} += "uhd-firmware"

PE = "1"
INC_PR = "r3"

inherit cmake pythonnative

PACKAGES =+ "${PN}-tests ${PN}-examples ${PN}-e1xx"

INSANE_SKIP_${PN} = "dev-so"

FILES_${PN} += "${libdir}/libuhd.so \
                ${datadir}/uhd/utils/*"
FILES_${PN}-tests += "${datadir}/uhd/tests/*"
FILES_${PN}-dbg += "${datadir}/uhd/examples/.debug/* \
                    ${datadir}/uhd/utils/.debug/* \
                    ${datadir}/uhd/tests/.debug/* \
                    ${datadir}/uhd/usrp_e_utils/.debug/*"
FILES_${PN}-examples = "${datadir}/uhd/examples/*"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[orc] = "-DENABLE_ORC=ON,-DENABLE_ORC=OFF,orc"

EXTRA_OECMAKE = "-DENABLE_E100=TRUE"

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"
