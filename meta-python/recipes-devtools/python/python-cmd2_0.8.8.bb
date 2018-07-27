SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60bc6842001870a418935bd84570b676"

SRC_URI[md5sum] = "1f555bb656d361d9b0212b07e232752f"
SRC_URI[sha256sum] = "b08bc5088fdd131d659354e6b4f8cf7c01a70566f68aed146e00d296a24b687b"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-doctest \
    ${PYTHON_PN}-pyparsing \
    ${PYTHON_PN}-pyperclip \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-stringold \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-textutils \
    "

BBCLASSEXTEND = "native nativesdk"
