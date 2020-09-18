SUMMARY = "Argcomplete provides easy, extensible command line tab completion of arguments for your Python script."
HOMEPAGE = "https://github.com/kislyuk/argcomplete"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI[md5sum] = "47e2a3db821f57165be8f3d3ec1e5259"
SRC_URI[sha256sum] = "2fbe5ed09fd2c1d727d4199feca96569a5b50d44c71b16da9c742201f7cc295c"

PYPI_PACKAGE = "argcomplete"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-core \
"

BBCLASSEXTEND = "native nativesdk"

