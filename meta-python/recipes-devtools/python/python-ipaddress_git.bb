DESCRIPTION = "Python 3.3+'s ipaddress for Python 2.6, 2.7, 3.2."
HOMEPAGE = "https://github.com/phihag/ipaddress"
SECTION = "devel/python"
LICENSE = "Python-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f538584cc3407bf76042def7168548a"

PV = "1.0.15"
SRCREV = "95adb9f374a1cca5efda08d20d9fc58d955d4a42"

SRCNAME = "ipaddress"
SRC_URI = "git://github.com/phihag/${SRCNAME}.git"

S = "${WORKDIR}/git"

inherit setuptools

DEPENDS += " \
        python-pip \
        "

RDEPENDS_${PN} += " \
        "
