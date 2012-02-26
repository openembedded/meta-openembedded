DESCRIPTION = "Vala is a C#-like language dedicated to ease GObject programming. \
Vala compiles to plain C and has no runtime environment nor penalities whatsoever."
SECTION = "devel"
DEPENDS = "glib-2.0 dbus"
BBCLASSEXTEND = "native"
DEPENDS_virtclass-native = "glib-2.0-native dbus-native"
HOMEPAGE = "http://vala-project.org"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
INC_PR = "r0"

#
# WARNING: This source release has specifically been built for OpenEmbedded.
# Don't update to any upstream release without consulting the recipe maintainer.
#

SRC_URI = "\
  http://downloads.freesmartphone.org/sources/vala-${PV}.tar.bz2;name=archive \
"

inherit autotools

EXTRA_OECONF = "--disable-vapigen"

FILES_${PN}-doc += "${datadir}/devhelp"
