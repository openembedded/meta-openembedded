DESCRIPTION = "Manage dynamic plugins for Python applications"
HOMEPAGE = "https://docs.openstack.org/stevedore/latest/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "6005f32c09dc3159802a61c04862770e439e32db22f6da78ba56e8a735466882"

DEPENDS += "python3-pbr-native"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"
