DESCRIPTION = "CAN BUS tools in Python 3."
HOMEPAGE = "https://github.com/eerimoq/cantools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9aa4ec07de78abae21c490c9ffe61bd"

SRC_URI[sha256sum] = "651b6ea67999c1106a582fd54c92b5b27a1a5f2896e0058beeb96a4f318cb33f"

PYPI_PACKAGE = "cantools"

inherit pypi setuptools3

CLEANBROKEN = "1"

