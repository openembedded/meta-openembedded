DESCRIPTION = "Internationalised Domain Names in Applications"
HOMEPAGE = "https://github.com/kjd/idna"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause & Python-2.0 & Unicode"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=c61e6ec7ab3da6b340d8d3a89c964ef2"

PV = "2.0"
SRCREV = "bb6e94c5b5525684d8ca010bf8ab42480c319e0a"

SRCNAME = "idna"
SRC_URI = "git://github.com/kjd/${SRCNAME}.git"

S = "${WORKDIR}/git"

inherit setuptools

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        "
