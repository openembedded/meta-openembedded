LICENSE = "BSD"
PV = "0.1.0+svnr${SRCPV}"
PR = "${INC_PR}.0"

require e-module.inc

do_configure_prepend() {
       sed -i -e /po/d -e /AM_GNU_GETTEXT/d configure.ac
       sed -i -e s:\ po::g Makefile.am
}

# Calls /usr/bin/eject for media
RRECOMMENDS_${PN} += "eject"
