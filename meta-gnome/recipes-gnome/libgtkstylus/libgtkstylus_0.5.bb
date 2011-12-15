DESCRIPTION = "GTK plugin for stylus based systems"
SECTION = "libs"
DEPENDS = "gtk+"
LICENSE = "LGPL-2.0+"
LIC_FILES_CHKSUM = "file://tap.c;beginline=1;endline=20;md5=71756eeb144e9eeb177c69aa672b1635"
PR = "r3"

inherit autotools

SRC_URI = "http://burtonini.com/temp/${PN}-${PV}.tar.gz \
	file://gtkstylus.sh"

do_install_append() {
	install -d ${D}/${sysconfdir}/X11/Xsession.d
	install -m 755 ${WORKDIR}/gtkstylus.sh ${D}/${sysconfdir}/X11/Xsession.d/45gtkstylus
}

# Horrible but rpm falls over if you use '*'
GTKVER = "2.10.0"

FILES_${PN} = "${sysconfdir} \
               ${libdir}/gtk-2.0/${GTKVER}/modules/libgtkstylus.so.*"
FILES_${PN}-dbg += "${libdir}/gtk-2.0/${GTKVER}/modules/.debug"
FILES_${PN}-dev += "${libdir}/gtk-2.0/${GTKVER}/modules/libgtkstylus.so \
                    ${libdir}/gtk-2.0/${GTKVER}/modules/libgtkstylus.*a"
