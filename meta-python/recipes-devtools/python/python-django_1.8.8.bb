SUMMARY = "A high-level Python Web framework"
HOMEPAGE = "http://www.djangoproject.com/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f09eb47206614a4954c51db8a94840fa"

SRCNAME = "Django"

SRC_URI = "https://pypi.python.org/packages/source/D/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "08ecf83b7e9d064ed7e3981ddc3a8a15"
SRC_URI[sha256sum] = "8255242fa0d9e0bf331259a6bdb81364933acbe8863291661558ffdb2fc9ed70"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

FILES_${PN} += "${datadir}/django"

BBCLASSEXTEND = "nativesdk"
