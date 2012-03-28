DESCRIPTION = "ATA S.M.A.R.T. Reading and Parsing Library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1"

PR = "r1"

DEPENDS = "udev"

SRCREV = "v${PV}"
SRC_URI = "git://git.0pointer.de/libatasmart.git;protocol=git \
	file://strpool.patch"

S = "${WORKDIR}/git"

inherit autotools lib_package

do_install_append() {
	sed -i -e s://:/:g -e 's:=${libdir}/libudev.la:-ludev:g' ${D}${libdir}/libatasmart.la
}

PACKAGES =+ "${PN}-dev-vala"
FILES_${PN}-dev-vala = "${datadir}/vala"
