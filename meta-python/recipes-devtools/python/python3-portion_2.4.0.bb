DESCRIPTION = "Python data structure and operations for intervals"
HOMEPAGE = "https://github.com/AlexandreDecan/portion"
SECTION = "devel/python"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=05f1e16a8e59ce3e9a979e881816c2ab"

inherit pypi setuptools3

SRC_URI[sha256sum] = "deb16389e844dbf9aeb654261fce5febd720e4786c6690efbb9dc11608226840"

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-sortedcontainers \
"

BBCLASSEXTEND = "native"
