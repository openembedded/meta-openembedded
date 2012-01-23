DESCRIPTION = "Babl is a dynamic, any to any, pixel format conversion library."
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

inherit gnome

SRC_URI = "ftp://ftp.gimp.org/pub/${PN}/0.1/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "dc960981a5ec5330fc1c177be9f59068"
SRC_URI[sha256sum] = "319b9004a5366101fd522da02cc983a5d0f50a17af0fa5cbce3535e31bce19a4"

FILES_${PN} += "${libdir}/babl-*/*.so"
FILES_${PN}-dev += "${libdir}/babl-*/*.la"
FILES_${PN}-dbg += "${libdir}/babl-*/.debug/"
