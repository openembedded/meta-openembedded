SUMMARY = "Tiny image-viewer"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/ristretto"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=35d145429ad3cbf5308d1dc93f66376b"
DEPENDS = "exo libexif gtk+ dbus-glib libxfce4ui libxfce4util xfconf cairo"

inherit xfce-app

RRECOMMENDS_${PN} += "tumbler"

SRC_URI[md5sum] = "5332876b627b3237eebb76db66e2fb11"
SRC_URI[sha256sum] = "0b4c90c585166c958d479075b98da6e28ca500bda6f4d3443f20e0a2172030ee"

FILES_${PN} += "${datadir}/appdata"
