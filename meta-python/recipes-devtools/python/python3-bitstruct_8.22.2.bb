DESCRIPTION = "This module performs conversions between Python values and C bit field structs represented as Python byte strings."
HOMEPAGE = "https://github.com/eerimoq/bitstruct"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9aa4ec07de78abae21c490c9ffe61bd"

SRC_URI[sha256sum] = "ad3884a559871aa931d216ff45000b7ae29a5991af4fce1b24c7580d2169979b"

inherit pypi python_setuptools_build_meta

CLEANBROKEN = "1"

