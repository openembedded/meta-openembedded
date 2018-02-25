SUMMARY = "A Pure Python Expect like Module for Python"
HOMEPAGE = "http://pexpect.readthedocs.org/"
SECTION = "devel/python"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c7a725251880af8c6a148181665385b"

SRC_URI[md5sum] = "e9b07f0765df8245ac72201d757baaef"
SRC_URI[sha256sum] = "67b85a1565968e3d5b5e7c9283caddc90c3947a2625bed1905be27bd5a03e47d"

UPSTREAM_CHECK_URI = "https://pypi.python.org/pypi/pexpect"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-terminal \
    ${PYTHON_PN}-resource \
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-ptyprocess \
"

BBCLASSEXTEND = "native nativesdk"
