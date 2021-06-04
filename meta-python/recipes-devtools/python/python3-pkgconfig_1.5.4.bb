SUMMARY = "Python module to interface with the pkg-config command line too"
HOMEPAGE = "http://github.com/matze/pkgconfig"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faa7f82be8f220bff6156be4790344fc"

SRC_URI[sha256sum] = "c34503829fd226822fd93c902b1cf275516908a023a24be0a02ba687f3a00399"

RDEPENDS_${PN} = "pkgconfig \
                 ${PYTHON_PN}-shell \
                 "

inherit pypi setuptools3

BBCLASSEXTEND = "native"

