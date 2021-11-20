SUMMARY = "Tiny image-viewer"
HOMEPAGE = "https://docs.xfce.org/apps/ristretto/start"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=35d145429ad3cbf5308d1dc93f66376b"

DEPENDS = "exo libexif libxfce4ui libxfce4util xfconf cairo file"

inherit xfce-app mime-xdg

RRECOMMENDS:${PN} += "tumbler"

SRC_URI[sha256sum] = "47032da8216a83c1cb349bece7521494e7509469a82236cfeadc0a8469451001"

FILES:${PN} += "${datadir}/metainfo"
