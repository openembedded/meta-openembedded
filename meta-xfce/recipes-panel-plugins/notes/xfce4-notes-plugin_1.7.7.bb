SUMMARY = "Notes plugin for the Xfce Panel"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-notes-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-panel-plugin

DEPENDS = "gtk+ libxfce4ui xfce4-panel xfconf libunique"

SRC_URI += " \
    file://0001-Bump-dependency-to-Xfce-4.10.patch \
    file://0002-main-status-icon.c-remove-deprecated-g_type_init.patch \
"
SRC_URI[md5sum] = "42b924b23f2fec6a1099e9b7a87db4a3"
SRC_URI[sha256sum] = "a7baa105b37ad05dea4d6b55d98fd3214c77ad5c7a0e91471d4906c81e5f5217"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/*.so.*"

# *.so are required for plugin detection
INSANE_SKIP_${PN} = "dev-so"
