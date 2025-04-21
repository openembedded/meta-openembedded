SUMMARY = "Extended Module Player Library"
HOMEPAGE = "http://xmp.sourceforge.net/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=59;md5=ea030bd80f99071b0d3f9a9f752d1ca8"

inherit cmake pkgconfig

SRC_URI = "git://github.com/libxmp/libxmp.git;protocol=https;branch=master"
SRCREV = "828ef357943e1fbb13910e7a6fca21987c5c5827"

S = "${WORKDIR}/git"
