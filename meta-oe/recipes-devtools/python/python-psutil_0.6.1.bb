SUMMARY = "A cross-platform process and system utilities module for Python"
SECTION = "devel/python"
HOMEPAGE = "http://code.google.com/p/psutil"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI = "http://psutil.googlecode.com/files/psutil-${PV}.tar.gz"
SRC_URI[md5sum] = "3cfcbfb8525f6e4c70110e44a85e907e"
SRC_URI[sha256sum] = "d665a4cc58c9a5d207fb0dc9869fc0ee10f4f66ad885e84886ef6339ccce0a6f"

S = "${WORKDIR}/psutil-${PV}"

inherit setuptools

