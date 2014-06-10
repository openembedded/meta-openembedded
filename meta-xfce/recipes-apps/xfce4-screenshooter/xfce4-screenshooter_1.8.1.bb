SUMMARY = "Application to take screenshots"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-screenshooter"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "xfce4-panel libxfce4util libxfce4ui gdk-pixbuf gtk+ glib-2.0 libsoup-2.4 exo libxfixes xext virtual/libx11"

inherit xfce-app

SRC_URI += "file://0001-Fix-panel-plugin-build.patch"
SRC_URI[md5sum] = "d0ffea2052a8e70154cf13789070711f"
SRC_URI[sha256sum] = "40419892bd28989315eed053c159bba0f4264ed8c6c6738806024e481eab9492"

do_compile_prepend() {
	mkdir -p lib
}

FILES_${PN} += "${datadir}/xfce4/panel/plugins \
        ${libdir}/xfce4/panel/plugins"
