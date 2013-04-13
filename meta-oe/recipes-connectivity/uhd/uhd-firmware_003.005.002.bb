DESCRIPTION = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://share/uhd/images/usrp_e100_fpga_v2.bin;md5=29260868c9484a37a7e6b61b29c57a4a"

inherit allarch

SRC_URI = "http://files.ettus.com/binaries/uhd_stable/releases/uhd_${PV}-release/uhd-images_${PV}-release.tar.gz"
SRC_URI[md5sum] = "2dbb7e2fb0c774e5ea2372bac25102e5"
SRC_URI[sha256sum] = "217efa814fb031ad136bd3dcf89ca219dd6d7ee01aa7e65c56c56b77379117bd"

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

