SUMMARY = "Babl is a dynamic, any to any, pixel format conversion library"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

inherit gnomebase

SRC_URI = "http://ftp.gimp.org/pub/${BPN}/0.1/${BP}.tar.bz2"
SRC_URI[md5sum] = "9e1542ab5c0b12ea3af076a9a2f02d79"
SRC_URI[sha256sum] = "943fc36ceac7dd25bc928256bc7b535a42989c6b971578146869eee5fe5955f4"

FILES_${PN} += "${libdir}/babl-*/*.so"
FILES_${PN}-dev += "${libdir}/babl-*/*.la"
FILES_${PN}-dbg += "${libdir}/babl-*/.debug/"
