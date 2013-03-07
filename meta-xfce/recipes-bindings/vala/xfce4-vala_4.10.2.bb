DESCRIPTION = "Xfce4 Vala provides bindings for the Xfce framework"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=243b725d71bb5df4a1e5920b344b86ad"
DEPENDS = "libxfce4util garcon xfconf libxfce4ui xfce4-panel exo vala xfce4-dev-tools-native"

inherit xfce

SRC_URI = " \
    http://archive.xfce.org/src/bindings/${BPN}/${@xfce_verdir("${PV}")}/${BPN}-${PV}.tar.bz2 \
    file://0001-align-vapi-dir.patch \
"
SRC_URI[md5sum] = "afcca7cb5ae84b9a9a621a352eaf59c4"
SRC_URI[sha256sum] = "7d9ee9f7f4ce88f3ebc1bd465bad1cd813fa5d268be62fc8d313cbec5345a978"

FILES_${PN} += "${datadir}/vala"

RDEPENDS_${PN} = "vala"
