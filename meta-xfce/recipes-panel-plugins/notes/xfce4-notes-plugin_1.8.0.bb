SUMMARY = "Notes plugin for the Xfce Panel"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-notes-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-panel-plugin

DEPENDS = "gtk+ libxfce4ui xfce4-panel xfconf libunique"

SRC_URI += " \
    file://0001-main-status-icon.c-remove-deprecated-g_type_init.patch \
"
SRC_URI[md5sum] = "569341bc27e2e46d23b6bffc14bff41a"
SRC_URI[sha256sum] = "3627b7da0533ca3fb10310f287dc0d569470960ffdaea7678fd878a20d409678"

FILES_${PN} += "${libdir}/xfce4/panel-plugins/*.so.*"

# *.so are required for plugin detection
INSANE_SKIP_${PN} = "dev-so"
