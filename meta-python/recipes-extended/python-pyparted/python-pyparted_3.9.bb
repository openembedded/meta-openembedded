DESCRIPTION = "pyparted is a set of Python modules that provide Python programmers \
an interface to libparted, the GNU parted library for disk partitioning and \
filesystem manipulation."
SUMMARY = "Python bindings for libparted"
HOMEPAGE = "https://fedorahosted.org/pyparted/"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b \
    file://src/_pedmodule.c;startline=10;endline=22;md5=70c62bd73782a03f56a0571a9f08ea46 \
"
DEPENDS += "parted"

SRC_URI = "https://fedorahosted.org/releases/p/y/pyparted/pyparted-${PV}.tar.gz"
SRC_URI[md5sum] = "f16c7ef7f5fa4a43fcb2a4654b487e39"
SRC_URI[sha256sum] = "a56712e3d058ce3d859c158236dbbf45224018919efd3d880ea80f9e0d0bebbb"
S = "${WORKDIR}/pyparted-${PV}"

inherit distutils

RDEPENDS_${PN} += "python-stringold python-codecs python-math"
RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND += "native"
