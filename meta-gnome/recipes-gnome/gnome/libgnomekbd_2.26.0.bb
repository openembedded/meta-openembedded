DESCRIPTION = "GNOME keyboard library"
LICENSE = "LGPL"

DEPENDS = "gconf-dbus dbus libxklavier gtk+"

inherit gnome

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-I$(includedir)':'-I.':g
        find ${S} -name Makefile | xargs sed -i s:'-I/usr/include':'-I${STAGING_INCDIR}':g
}



