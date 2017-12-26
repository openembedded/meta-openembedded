SUMMARY = "Extra features for standard library's cmd module"
HOMEPAGE = "http://packages.python.org/cmd2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=49;endline=49;md5=0f13c9cfc75288223cf7edd2f3b985a2"

SRC_URI[md5sum] = "afa5c55fd94bb4a38a01232a511585eb"
SRC_URI[sha256sum] = "e132db7d1f5a2faf2310606ee4b2e49c7b4e1e5ed533f1e4ca9474c51475eb0f"

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
