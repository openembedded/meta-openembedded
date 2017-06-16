SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=43;endline=43;md5=0f13c9cfc75288223cf7edd2f3b985a2"

SRC_URI[md5sum] = "4a5b5aab407bdeb49634789f9a42902b"
SRC_URI[sha256sum] = "d06301cc578a83531261121c0b11d79d29d0a80aca01ed6752c20c4cfcda2dd9"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-doctest \
    ${PYTHON_PN}-pyparsing \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-stringold \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-textutils \
    "
