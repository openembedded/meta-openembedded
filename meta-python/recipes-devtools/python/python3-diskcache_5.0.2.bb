DESCRIPTION = "Disk Cache -- Disk and file backed persistent cache."
HOMEPAGE = "http://www.grantjenks.com/docs/diskcache/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c985b6a9269e57a1073d5f142d68eb68"

SRC_URI[md5sum] = "b8061d293045b29b83201cf9a62b1da2"
SRC_URI[sha256sum] = "6e350e795e197f979316a3f524a729edb13d1739959c6e9cb5df78a0a72c8255"

PYPI_PACKAGE = "diskcache"

inherit pypi setuptools3

CLEANBROKEN = "1"

