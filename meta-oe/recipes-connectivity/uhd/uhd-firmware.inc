DESCRIPTION = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"
LICENSE = "GPLv3"

INC_PR = "r0"

PACKAGE_ARCH = "all"

SRC_URI = "http://www.ettus.com/downloads/uhd_images/UHD-images-${PV}/UHD-images-${PV}-Linux.tar.gz"

S = ${WORKDIR}/UHD-images-${PV}-Linux

do_install() {
	install -d ${D}${datadir}/uhd/images
	install -m 0644 ${S}/share/uhd/images/* ${D}${datadir}/uhd/images
}

PACKAGES = "${PN}"
FILES_${PN} = ${datadir}/uhd/images

