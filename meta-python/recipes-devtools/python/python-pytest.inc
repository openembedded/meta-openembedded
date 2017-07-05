SUMMARY = "Simple powerful teting with python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c39b24965f4aef64222cb35de9d47cc4"

SRC_URI[md5sum] = "35356e6e31cbd8318e8b2a8340a90b72"
SRC_URI[sha256sum] = "095e1832f7e424563c95daf4d8d3c865052b80e139cdd2f9610a986ee8526206"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
