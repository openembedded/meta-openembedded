DESCRIPTION = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://share/uhd/images/usrp_e100_fpga_v2.bin;md5=8df143216c46b7c49804f95056c7057a"

inherit allarch

SRC_URI = "http://files.ettus.com/binaries/uhd_stable/releases/uhd_${PV}-release/uhd-images_${PV}-release.tar.gz"
SRC_URI[md5sum] = "dd2373263740d2d48fb39e57846bcd2a"
SRC_URI[sha256sum] = "352fbf69fbfff03a02b5d9b3c398771db40e4c5b77f99f0fdbff67c547cc0d18"

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

