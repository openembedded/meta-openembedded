DESCRIPTION = "libgee is a collection library providing GObject-based interfaces \
and classes for commonly used data structures."
HOMEPAGE = "http://live.gnome.org/Libgee"
SECTION = "libs"
DEPENDS = "glib-2.0"
BBCLASSEXTEND = "native"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
INC_PR = "r6"
PE = "1"

inherit autotools vala pkgconfig
do_configure_prepend() {
    MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"
    for i in ${MACROS}; do
        rm -f m4/$i
    done
}
