DESCRIPTION = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://share/uhd/images/usrp_e100_fpga_v2.bin;md5=52f5a4b60aca734da1b0e78078aeea71"

inherit allarch

SRC_URI = "http://www.ettus.com/downloads/uhd_releases/${@'${PV}'.replace('.', '_')}/images-only/UHD-images-${PV}.tar.gz"
SRC_URI[md5sum] = "84ec78ec03699a96d64210b93019f782"
SRC_URI[sha256sum] = "53712db43b324adad1b83617f99f7b73682bcd0078bbc9915d0a376f4ca41289"

S = "${WORKDIR}/UHD-images-${PV}-59f407f"

do_install() {
	install -d ${D}${datadir}/uhd/images
	install -d ${D}${datadir}/uhd/images/bit
	install -m 0644 ${S}/share/uhd/images/bit/* ${D}${datadir}/uhd/images/bit
	rm -rf ${S}/share/uhd/images/bit
	install -m 0644 ${S}/share/uhd/images/* ${D}${datadir}/uhd/images
}

PACKAGES = "${PN}"
FILES_${PN} = "${datadir}/uhd/images"

