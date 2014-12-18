# Imported from git://git.yoctoproject.org/meta-cloud-services

SUMMARY = "A Python Mocking and Patching Library for Testing"
HOMEPAGE = "https://pypi.python.org/pypi/mock"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=80e5ba73891255687dff3bee2b4cbb16"

SRCNAME = "mock"

SRC_URI = "https://pypi.python.org/packages/source/m/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "c3971991738caa55ec7c356bbc154ee2"
SRC_URI[sha256sum] = "b839dd2d9c117c701430c149956918a423a9863b48b09c90e30a6013e7d2f44f"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} += " \
    python-prettytable \
    python-cmd2 \
    python-pyparsing \
    python-mccabe \
    python-pep8 \
    python-pyflakes"
