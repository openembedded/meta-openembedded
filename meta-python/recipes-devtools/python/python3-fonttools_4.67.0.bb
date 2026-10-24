SUMMARY = "Tools to manipulate font files"
DESCRIPTION = "A library to manipulate font files from Python."
HOMEPAGE = "http://github.com/fonttools/fonttools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=211c9e4671bde3881351f22a2901f692"

SRC_URI[sha256sum] = "3cb57e6600ca77c0b1729cf8adc23bc0652633a37f18cfa934d9c7bc3de25519"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core"

BBCLASSEXTEND = "native nativesdk"
