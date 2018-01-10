SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=50;endline=50;md5=0f13c9cfc75288223cf7edd2f3b985a2"

SRC_URI[md5sum] = "eeb80d5c29715b4ac39ecc032842a25f"
SRC_URI[sha256sum] = "f518d30c641483c8d6c246afae6e4447f816f8300befc6a11c476eeb62a496e6"

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
