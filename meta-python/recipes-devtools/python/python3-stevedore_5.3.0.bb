DESCRIPTION = "Manage dynamic plugins for Python applications"
HOMEPAGE = "https://docs.openstack.org/stevedore/latest/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "9a64265f4060312828151c204efbe9b7a9852a0d9228756344dbc7e4023e375a"

DEPENDS += "python3-pbr-native"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-pbr python3-six"

BBCLASSEXTEND = "native"
