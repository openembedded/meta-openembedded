SUMMARY = "Tools to manipulate font files"
DESCRIPTION = "A library to manipulate font files from Python."
HOMEPAGE = "http://github.com/fonttools/fonttools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=211c9e4671bde3881351f22a2901f692"

SRC_URI[sha256sum] = "d09a2ea8ed4c71ac52e789ba9313da3e26cee3f812400d7e8a8b3a38d0f98dfe"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core"

BBCLASSEXTEND = "native nativesdk"
