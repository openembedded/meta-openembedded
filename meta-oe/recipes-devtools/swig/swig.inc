DESCRIPTION = "SWIG - Simplified Wrapper and Interface Generator"
HOMEPAGE = "http://swig.sourceforge.net/"
LICENSE = "BSD"
SECTION = "devel"
INC_PR = "r1"

DEPENDS = "python"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.gz"

inherit autotools

BBCLASSEXTEND = "native"

do_configure() {
	oe_runconf
}

