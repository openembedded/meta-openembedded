DESCRIPTION = "Classes Without Boilerplate"
HOMEPAGE = "http://www.attrs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d4ab25949a73fe7d4fdee93bcbdbf8ff"

SRC_URI[sha256sum] = "0ef97238856430dcf9228e07f316aefc17e8939fc8507e18c6501b761ef1a42a"
SRC_URI[md5sum] = "ab7533fc91c5db3d5b65e66be907444e"

inherit pypi setuptools3

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-crypt \
    ${PYTHON_PN}-ctypes \
"
RDEPENDS_${PN}_class-nativesdk += " \
    ${PYTHON_PN}-crypt \
    ${PYTHON_PN}-ctypes \
"

BBCLASSEXTEND = "native nativesdk"
