SUMMARY = "xdg-dbus-proxy is a filtering proxy for D-Bus connections"
HOMEPAGE = "https://github.com/flatpak/xdg-dbus-proxy"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = " \
    glib-2.0 \
    libxslt-native \
    docbook-xsl-stylesheets-native \
"

inherit meson pkgconfig

SRC_URI = "git://github.com/flatpak/xdg-dbus-proxy.git;protocol=https;branch=main;tag=${PV}"

SRCREV = "6a170fa77e3cbecb48f9dd2478fe5c0a119eb467"

CVE_STATUS[CVE-2026-34080] = "fixed-version: fixed in 0.1.7"

BBCLASSEXTEND = "native"
