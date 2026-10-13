SUMMARY = "module for creating simple ASCII tables"
HOMEPAGE = "https://github.com/foutaise/texttable/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a97cdac2d9679ffdcfef3dc036d24f6"

inherit pypi setuptools3

SRC_URI[sha256sum] = "ce71fc5928ede6cd7a60dbe3cb2845e6df8f5598fe435252ea1b31722415fda7"

BBCLASSEXTEND = "native nativesdk"
