DESCRIPTION = "Xfce4 settings"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo garcon gtk+ libxfce4util libxfce4ui xfconf dbus-glib libxi virtual/libx11 xrandr libnotify libxcursor libxklavier"
PR = "r3"

inherit xfce

SRC_URI += "file://0001-xsettings.xml-remove-trouble-causing-comment.patch \
            file://0002-xsettings.xml-Set-default-themes.patch \
            file://0003-Remember-the-settings-manager-window-size-bug-9384.patch \
            file://touchscreen/invisible \
            file://touchscreen/wait \
            file://touchscreen/0001-add-cursor-theme-xfce-invisible.patch \
            file://touchscreen/0002-mouse-settings-dialog-add-touchscreen-pointer-option.patch \
            file://touchscreen/0003-XfcePointersHelper-gets-a-pointer-to-XfceXSettingsHe.patch \
            file://touchscreen/0004-XfceXSettingsHelper-gets-a-property-touchscreen-poin.patch \
            file://touchscreen/0005-pointers-detect-a-change-of-pointer-device-used-and-.patch \
"
SRC_URI[md5sum] = "cc4dd9179ead9046c056431f01a12000"
SRC_URI[sha256sum] = "0843f09ba9673f1d1b5df8dce4a17b63c183a9ba3be75fb6ef99a67fc8c1f77e"

FILES_${PN} += "${libdir}/xfce4"

do_install_prepend() {
	# somehow binary files are not patched correctly by oe-patch - so copy them
	cp ${WORKDIR}/touchscreen/invisible ${S}/cursors
	cp ${WORKDIR}/touchscreen/wait ${S}/cursors
}

RRECOMMENDS_${PN} += "gnome-icon-theme"
