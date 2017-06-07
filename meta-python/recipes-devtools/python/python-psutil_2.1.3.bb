SUMMARY = "A cross-platform process and system utilities module for Python"
SECTION = "devel/python"
HOMEPAGE = "https://github.com/giampaolo/psutil"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f02e99f7f3c9a7fe8ecfc5d44c2be62"

SRC_URI = "https://pypi.python.org/packages/source/p/psutil/psutil-${PV}.tar.gz"
SRC_URI[md5sum] = "015a013c46bb9bc30b5c344f26dea0d3"
SRC_URI[sha256sum] = "b434c75f01715777391f10f456002e33d0ca14633f96fdbd9ff9139b42d9452c"

S = "${WORKDIR}/psutil-${PV}"

inherit setuptools
