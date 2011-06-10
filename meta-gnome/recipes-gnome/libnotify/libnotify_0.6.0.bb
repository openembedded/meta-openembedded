DESCRIPTION = "Send desktop notifications to a notification daemon"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "dbus gtk+"

inherit gnome
SRC_URI[archive.md5sum] = "732c9d2cd5eb6a9069264a319d330516"
SRC_URI[archive.sha256sum] = "73b16a79bb2fed62dcf146b11c4405776cb96a09dce66379ca541da7dbd64210"

do_configure_prepend() {
	sed -i /GTK3/d configure.ac
	sed -i -e /test-gtk3/d -e 's/test-xy-stress \\/test-xy-stress/' tests/Makefile.am
}
