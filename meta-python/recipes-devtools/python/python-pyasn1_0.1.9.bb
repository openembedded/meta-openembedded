DESCRIPTION = "Python library implementing ASN.1 types."
HOMEPAGE = "http://pyasn1.sourceforge.net/"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=425e62320d430219736139b134db2fc4"
DEPENDS = "python"

SRC_URI = "https://pypi.python.org/packages/source/p/pyasn1/pyasn1-${PV}.tar.gz"
SRC_URI[md5sum] = "f00a02a631d4016818659d1cc38d229a"
SRC_URI[sha256sum] = "853cacd96d1f701ddd67aa03ecc05f51890135b7262e922710112f12a2ed2a7f"

S = "${WORKDIR}/pyasn1-${PV}"

inherit setuptools

RDEPENDS_${PN} += "python-lang python-shell"
