SUMMARY = "Serial Port Support for Python"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7424386ffe323e815ee62ee9ad591dd8"
SRCNAME = "pyserial"
PR = "ml4"

SRC_URI = "${SOURCEFORGE_MIRROR}/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

# FIXME might stop packaging serialwin32 and serialjava files

RDEPENDS_${PN} = "\
    python-fcntl \
    python-io \
    python-stringold \
"

SRC_URI[md5sum] = "eec19df59fd75ba5a136992897f8e468"
SRC_URI[sha256sum] = "6b6a9e3d2fd5978c92c843e0109918a4bcac481eecae316254481c0e0f7e73c8"
