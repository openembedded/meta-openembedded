SUMMARY = "Simple powerful teting with python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c39b24965f4aef64222cb35de9d47cc4"

SRC_URI[md5sum] = "635976c2a9b2c2599d69fb0ad5abaf7d"
SRC_URI[sha256sum] = "4c2159d2be2b4e13fa293e7a72bdf2f06848a017150d5c6d35112ce51cfd74ce"

SRC_URI_append = " file://0001-setup.py-remove-the-setup_requires-for-setuptools-scm.patch"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/python-pytest:"

BBCLASSEXTEND = "native nativesdk"
