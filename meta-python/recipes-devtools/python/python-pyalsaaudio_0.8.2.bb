SUMMARY = "Support for the Linux 2.6.x ALSA Sound System"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1a3b161aa0fcec32a0c8907a2219ad9d"

SRC_URI[md5sum] = "1f22415a3d8da4b303182ad81c2d062e"
SRC_URI[sha256sum] = "b6df486f1c4035041a5f3800496b86c64e48e4a7d47f94dcbca11b0187aa3a15"

DEPENDS += "alsa-lib"

inherit pypi distutils

RDEPENDS_${PN} += "alsa-lib"
