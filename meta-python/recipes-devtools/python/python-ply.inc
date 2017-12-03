SUMMARY = "Python Lex and Yacc"
DESCRIPTION = "Python ply: PLY is yet another implementation of lex and yacc for Python"
HOMEPAGE = "https://pypi.python.org/pypi/ply"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README.md;beginline=3;endline=30;md5=fcb04bc2f765e46ab7084d6ab6e452bb"

SRC_URI[md5sum] = "1d63c166ab250bab87d8dcc42dcca70e"
SRC_URI[sha256sum] = "96e94af7dd7031d8d6dd6e2a8e0de593b511c211a86e28a9c9621c275ac8bacb"

inherit pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native"
