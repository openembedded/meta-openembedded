SUMMARY = "Extended Module Player Library"
HOMEPAGE = "http://xmp.sourceforge.net/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=59;md5=b25470e2863502c9ac9886f09c21a8f9"

inherit cmake pkgconfig

SRC_URI = "git://github.com/libxmp/libxmp.git;protocol=https;branch=master;tag=libxmp-${PV}"
SRCREV = "1e0812137e4f9e15cc026c0c75af82d71d3cb5ad"

