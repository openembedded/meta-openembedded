SUMMARY = "A library for collecting system monitoring data"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

GNOMEBASEBUILDCLASS = "autotools"
inherit gnomebase lib_package gtk-doc gobject-introspection gettext upstream-version-is-even features_check

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

SRC_URI[archive.sha256sum] = "d3c18158549218835a00517d9c454a2de825ac7808efe84101a8b7e2a8ab6663"

UPSTREAM_CHECK_REGEX = "libgtop-(?P<pver>\d+(\.\d+)+).tar"

DEPENDS = "glib-2.0"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES','x11','xauth','',d)}"
PACKAGECONFIG[xauth] = ",,libxau"
