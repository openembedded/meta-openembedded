SUMMARY = "Recipe to embedded the Python PiP Package icontract"
HOMEPAGE = "https://pypi.org/project/icontract"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1d4a9b1f6b84bedf7a38843931e0dd57"

PR = "r0"

inherit pypi setuptools3
PYPI_PACKAGE = "icontract"
SRC_URI[sha256sum] = "281ec16f1d09bbcca7a4227e82cd10b4d5fb291f638df77c29b7acf493dd3178"

RDEPENDS:${PN} += "python3-asttokens"

BBCLASSEXTEND = "native"
