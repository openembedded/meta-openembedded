SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=50;endline=50;md5=0f13c9cfc75288223cf7edd2f3b985a2"

SRC_URI[md5sum] = "0988674436fde36bbfc5cd650f4808ac"
SRC_URI[sha256sum] = "4b3a9d9f305039ca94deb93e0a54ba896f12d1aae7206c6283fe02c6dbb0b7ba"

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
