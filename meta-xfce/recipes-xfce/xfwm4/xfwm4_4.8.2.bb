DESCRIPTION="Xfce4 Window Manager"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"
DEPENDS = "virtual/libx11 libxpm gtk+ libxfce4util libxfce4ui xfconf libwnck dbus-glib startup-notification"

inherit xfce update-alternatives

# SRC_URI must follow inherited one
SRC_URI += " \
    file://relative-symlinks-docs.patch \
"
SRC_URI[md5sum] = "41b730d5abf39a69fc3b221728ddaa75"
SRC_URI[sha256sum] = "6db2a6148d63d45699dbf6403fb227f8cbd529dcec7ee0827342940c442b0747"

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

RDEPENDS_${PN} = "xfwm4-theme-default"
FILES_${PN} += "${libdir}/xfce4/xfwm4/helper-dialog \
                ${datadir}/xfwm4/defaults \
               "
FILES_${PN}-dbg += "${libexecdir}/xfce4/xfwm4/.debug/*"

