SUMMARY = "Recipe to embedded the Python PiP Package icontract"
HOMEPAGE = "https://pypi.org/project/icontract"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1d4a9b1f6b84bedf7a38843931e0dd57"

PR = "r0"

inherit pypi setuptools3
PYPI_PACKAGE = "icontract"
SRC_URI[sha256sum] = "c1fd55c7709ef18a2ee64313fe863be2668b53060828fcca3525051160c92691"

RDEPENDS:${PN} += "python3-asttokens"

BBCLASSEXTEND = "native"
