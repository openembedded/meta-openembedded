SUMMARY = "Babl is a dynamic, any to any, pixel format conversion library"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

GNOMEBASEBUILDCLASS = "meson"

GIR_MESON_OPTION = "enable-gir"

inherit setuptools3 gnomebase gobject-introspection

DEPENDS += "lcms"

SRC_URI = "https://download.gimp.org/pub/${BPN}/0.1/${BP}.tar.xz"
SRC_URI[md5sum] = "d032853f2678bc95054c39d4f02045c1"
SRC_URI[sha256sum] = "9a710b6950da37ada94cd9e2046cbce26de12473da32a7b79b7d1432fc66ce0e"

BBCLASSEXTEND = "native"
