SUMMARY = "World timezone definitions, modern and historical"
HOMEPAGE = "http://pythonhosted.org/pytz"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=39ea92752a35cf67d8a885d8e3af3c69"

inherit pypi
PYPI_PACKAGE_EXT = "zip"

SRC_URI[md5sum] = "7006b56c0d68a162d9fe57d4249c3171"
SRC_URI[sha256sum] = "fae4cffc040921b8a2d60c6cf0b5d662c1190fe54d718271db4eb17d44a185b7"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-doctest \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-pprint \
    ${PYTHON_PN}-threading \
"

BBCLASSEXTEND = "native nativesdk"
