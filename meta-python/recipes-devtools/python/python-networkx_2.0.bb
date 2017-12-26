DESCRIPTION = "Python package for creating and manipulating graphs and networks"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=050668f0cfbb54d1861e798b2104b161"

SRC_URI[md5sum] = "34f9cc01b2eca37f362673b8eb65316b"
SRC_URI[sha256sum] = "cd5ff8f75d92c79237f067e2f0876824645d37f017cfffa5b7c9678cae1454aa"

PYPI_PACKAGE_EXT = "zip"

inherit pypi setuptools

RDEPENDS_${PN} += "python-2to3"
