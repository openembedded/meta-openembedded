DESCRIPTION = "Python library implementing ASN.1 types."
HOMEPAGE = "http://pyasn1.sourceforge.net/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae098273b2cf8b4af164ac20e32bddf7"
DEPENDS = "python"

SRC_URI = "https://pypi.python.org/packages/source/p/pyasn1/pyasn1-${PV}.tar.gz"
SRC_URI[md5sum] = "2cbd80fcd4c7b1c82180d3d76fee18c8"
SRC_URI[sha256sum] = "e4f81d53c533f6bd9526b047f047f7b101c24ab17339c1a7ad8f98b25c101eab"

S = "${WORKDIR}/pyasn1-${PV}"

inherit setuptools

RDEPENDS_${PN} += "python-lang python-shell"
