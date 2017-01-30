SUMMARY = "World timezone definitions, modern and historical"
HOMEPAGE = "http://pythonhosted.org/pytz"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22b38951eb857cf285a4560a914b7cd6"

inherit pypi

SRC_URI[md5sum] = "cc9f16ba436efabdcef3c4d32ae4919c"
SRC_URI[sha256sum] = "9a43e20aa537cfad8fe7a1715165c91cb4a6935d40947f2d070e4c80f2dcd22b"

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-threading \
"

BBCLASSEXTEND = "native nativesdk"
