SUMMARY = "Xfce configuration daemon and utilities"
SECTION = "x11/wm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "dbus-glib libxfce4util perl intltool-native xfce4-dev-tools-native"

inherit xfce gtk-doc

EXTRA_OECONF += "PERL=${STAGING_DIR_TARGET}/usr/bin/perl"

SRC_URI += "file://0001-Simplify-checks.patch"
SRC_URI[md5sum] = "4ed48150a03fb5f42b455494307b7f28"
SRC_URI[sha256sum] = "175219a441cc7d0f210bbd1a3b0abba41598627cd9db27235811400c3e100576"

FILES_${PN} += "${libdir}/xfce4/xfconf/xfconfd \
                ${datadir}/dbus-1/services/org.xfce.Xfconf.service"
