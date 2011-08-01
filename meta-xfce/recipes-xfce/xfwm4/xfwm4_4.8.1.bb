DESCRIPTION="Xfce4 Window Manager"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "virtual/libx11 libxpm gtk+ libxfce4util libxfce4ui xfconf libwnck dbus-glib startup-notification"
RDEPENDS_${PN} = "xfwm4-theme-default"

PR = "r0"

inherit xfce update-alternatives

# SRC_URI must follow inherited one
SRC_URI += " \
    file://relative-symlinks-docs.patch \
"

EXTRA_OECONF += " --enable-startup-notification"

python populate_packages_prepend () {
	themedir = bb.data.expand('${datadir}/themes', d)
	do_split_packages(d, themedir, '^(.*)', 'xfwm4-theme-%s', 'XFWM4 theme %s', allow_dirs=True)
}

PACKAGES_DYNAMIC += "xfwm4-theme-*"

ALTERNATIVE_NAME = "x-window-manager"
ALTERNATIVE_LINK = "${bindir}/x-window-manager"
ALTERNATIVE_PATH = "${bindir}/xfce4-session"
ALTERNATIVE_PRIORITY = "30"

FILES_${PN} += "${libdir}/xfce4/xfwm4/helper-dialog \
                ${datadir}/xfwm4/defaults \
               "
FILES_${PN}-dbg += "${libexecdir}/xfce4/xfwm4/.debug/*"

SRC_URI[md5sum] = "447433dca613180ebd6cfed4beb1c1c9"
SRC_URI[sha256sum] = "6a3cbf6c92a0cc3bcaf7cf2565c35beae6354768ec26b4d616a3c2544a8f8499"
