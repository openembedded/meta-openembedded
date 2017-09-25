SUMMARY = "Simplifies building parse types based on the parse module"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d07323820cca0f1d192cbbf8a0516f95"
PYPI_PACKAGE = "parse_type"

SRC_URI[md5sum] = "3697afc1b29f2857f6c7dc9590f25ce1"
SRC_URI[sha256sum] = "9a624f3b51604423f5de5321da11b0ae73f368b2a3cb4b383a6cf84ca4e4d495"

RDEPENDS_${PN} += "python-parse"

inherit pypi setuptools
