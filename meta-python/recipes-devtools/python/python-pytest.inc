SUMMARY = "Simple powerful teting with python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d8a5609608d9a5a180705b0fa298ae01"

SRC_URI[md5sum] = "c4d179f89043cc925e1c169d03128e02"
SRC_URI[sha256sum] = "795ec29fbba70b22a593691ce8bcd4bdde2dc96e8099731f73c7d8bb3ce879bf"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
