SUMMARY = "Xfce4 Desktop Manager"
SECTION = "x11/base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libxfce4ui libwnck thunar garcon exo"

inherit xfce features_check

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[sha256sum] = "e31dcb0008ae55e99e42128a4694002203305287434a8ee0f9937e7298a4bd11"

PACKAGECONFIG ??= "notify"
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

FILES:${PN} += "${datadir}/backgrounds"
