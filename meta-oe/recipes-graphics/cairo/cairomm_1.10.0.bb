SUMMARY = "C++ bindings for Cairo graphics library"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c46bda00ffbb0ba1dac22f8d087f54d9"

PR = "r1"

inherit autotools pkgconfig

DEPENDS = "cairo libsigc++-2.0"

SRC_URI = "http://cairographics.org/releases/cairomm-${PV}.tar.gz;name=archive"
SRC_URI[archive.md5sum] = "9c63fb1c04c8ecd3c5e6473075b8c39f"
SRC_URI[archive.sha256sum] = "068d96c43eae7b0a3d98648cbfc6fbd16acc385858e9ba6d37b5a47e4dba398f"

FILES_${PN}-doc += "${datadir}/devhelp"
FILES_${PN}-dev += "${libdir}/cairomm-*/"

