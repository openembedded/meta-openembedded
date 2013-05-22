DESCRIPTION = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://share/uhd/images/usrp_e100_fpga_v2.bin;md5=e86f2186823b264efc627aafc0ad60a2"

inherit allarch

SRC_URI = "http://files.ettus.com/binaries/uhd_stable/releases/uhd_${PV}-release/uhd-images_${PV}-release.tar.gz"
SRC_URI[md5sum] = "65cf76fd363d576ceff9c1527ade297b"
SRC_URI[sha256sum] = "9079586def30b6805053092673d942c0051f1f99eebc676b871f070c04b66e35"

S = "${WORKDIR}/uhd-images_${PV}-release"

do_install() {
    install -d ${D}${datadir}/uhd/images
    install -d ${D}${datadir}/uhd/images/bit
    install -m 0644 ${S}/share/uhd/images/bit/* ${D}${datadir}/uhd/images/bit
    rm -rf ${S}/share/uhd/images/bit
    install -m 0644 ${S}/share/uhd/images/* ${D}${datadir}/uhd/images
}

PACKAGES = "${PN}"
FILES_${PN} = "${datadir}/uhd/images"

