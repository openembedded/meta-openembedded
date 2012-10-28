DESCRIPTION = "Vala is a C#-like language dedicated to ease GObject programming. \
Vala compiles to plain C and has no runtime environment nor penalities whatsoever."
SECTION = "devel"
DEPENDS = "glib-2.0 dbus gtk+"
BBCLASSEXTEND = "native"
DEPENDS_virtclass-native = "glib-2.0-native dbus-native"
HOMEPAGE = "http://vala-project.org"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
INC_PR = "r1"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/${BPN}/${SHRT_VER}/${BP}.tar.xz"
inherit autotools

EXTRA_OECONF = "--disable-vapigen"

FILES_${PN}-doc += "${datadir}/devhelp"
FILES_${PN} += "${datadir}/${BPN}-${SHRT_VER}/vapi"
