SUMMARY = "A Pure Python Expect like Module for Python"
HOMEPAGE = "http://pexpect.readthedocs.org/"
SECTION = "devel/python"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c7a725251880af8c6a148181665385b"

SRCNAME = "pexpect"

SRC_URI[md5sum] = "047a486dcd26134b74f2e67046bb61a0"
SRC_URI[sha256sum] = "00ab0872f80f5db740499e7a1283a7c3b97bea542d72df84d83dea17d0afd2d9"

UPSTREAM_CHECK_URI = "https://pypi.python.org/pypi/pexpect"

S = "${WORKDIR}/${SRCNAME}-${PV}"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-terminal \
    ${PYTHON_PN}-resource \
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-ptyprocess \
"

BBCLASSEXTEND = "native nativesdk"
