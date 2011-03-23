LICENSE = "LGPLv2"
PV = "0.0.1+svnr${SRCPV}"

require e-module.inc

DEPENDS += "elementary exalt edje-native"

CFLAGS += " -I${STAGING_INCDIR}/exalt -I${STAGING_INCDIR}/exalt_dbus"

do_configure_prepend() {
	sed -i -e /po/d configure.ac 
	sed -i -e s:\ po::g Makefile.am
}

FILES_${PN} += "${libdir}/enlightenment/modules/*/*.desktop \
                ${libdir}/enlightenment/modules/*/*.edj \
                ${libdir}/enlightenment/modules/*/*/*.so"

FILES_${PN}-static += "${libdir}/enlightenment/modules/*/*/*.a"
FILES_${PN}-dev += "${libdir}/enlightenment/modules/*/*/*.la"
FILES_${PN}-dbg += "${libdir}/enlightenment/modules/*/*/.debug"
 

