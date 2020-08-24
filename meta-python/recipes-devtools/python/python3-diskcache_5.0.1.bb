DESCRIPTION = "Disk Cache -- Disk and file backed persistent cache."
HOMEPAGE = "http://www.grantjenks.com/docs/diskcache/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c985b6a9269e57a1073d5f142d68eb68"

SRC_URI[md5sum] = "5c8c5789f584a36eacc7240921eee35c"
SRC_URI[sha256sum] = "b24a2da03d6a45edcec14ea6ef7129ddf0de78f04497557308d1461753d90633"

PYPI_PACKAGE = "diskcache"

inherit pypi setuptools3

CLEANBROKEN = "1"

