DESCRIPTION = "Manage dynamic plugins for Python applications"
HOMEPAGE = "https://docs.openstack.org/stevedore/latest/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "d31496a4f4df9825e1a1e4f1f74d19abb0154aff311c3b376fcc89dae8fccd73"

DEPENDS += "python3-pbr-native"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-pbr python3-six"

BBCLASSEXTEND = "native"
