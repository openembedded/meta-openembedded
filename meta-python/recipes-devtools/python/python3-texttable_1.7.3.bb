SUMMARY = "module for creating simple ASCII tables"
HOMEPAGE = "https://github.com/foutaise/texttable/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a97cdac2d9679ffdcfef3dc036d24f6"

inherit pypi setuptools3

SRC_URI[sha256sum] = "ff99aa7562c6cbcd0982cb6fae9145126b19878182496cdc46b8d6816721c5da"

BBCLASSEXTEND = "native nativesdk"
