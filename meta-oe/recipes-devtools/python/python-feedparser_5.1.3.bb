SUMMARY = "Python Atom and RSS feed parser"
HOMEPAGE = "http://code.google.com/p/feedparser"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f5fbe7f8b71b4019eca5ac5f900fd8ad"

SRC_URI = "http://feedparser.googlecode.com/files/feedparser-${PV}.tar.bz2"
SRC_URI[md5sum] = "6fb6372a1dc2f56d4d79d740b8f49f25"
SRC_URI[sha256sum] = "7f6507d400d07edfd1ea8205da36808009b0c539f5b8a6e0ab54337b955e6dc3"

S = "${WORKDIR}/feedparser-${PV}"

inherit setuptools

