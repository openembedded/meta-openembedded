SUMMARY = "GNOME Authentication Agent for PolicyKit"
DESCRIPTION = "PolicyKit-gnome provides an Authentication Agent for PolicyKit that integrates well with the GNOME desktop environment"
HOMEPAGE = "http://www.packagekit.org/"
BUGTRACKER = "http://bugzilla.gnome.org/"
DEPENDS = "polkit dbus-glib gconf gtk+ intltool-native gnome-common"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=74579fab173e4c5e12aac0cd83ee98ec \
                    file://src/main.c;beginline=1;endline=20;md5=aba145d1802f2329ba561e3e48ecb795"

SRC_URI = "http://hal.freedesktop.org/releases/polkit-gnome-${PV}.tar.bz2 \
"

PR = "r2"

EXTRA_OECONF = "\
    --disable-examples \
    --disable-introspection \
"

inherit autotools gtk-doc pkgconfig

FILES_${PN} += " ${datadir}/dbus-1 \
                 ${datadir}/PolicyKit \
"
SRC_URI[md5sum] = "f6b485ffd7bd605af815fd2747180481"
SRC_URI[sha256sum] = "81caa6972e651e90ef4ac31d7ed41bc79543d46b850dbd5b14b40f8ef7107d11"
