LICENSE = "MIT"
PV = "0.0.1+svnr${SRCPV}"
PR = "${INC_PR}.0"

require e-module.inc

SRCNAME = "E-MODULES-EXTRA/efm_path"

do_configure_prepend() {
    sed -i -e /po/d configure.ac
    sed -i -e s:\ po::g Makefile.am
}

FILES_${PN}-dbg += "${libdir}/enlightenment/modules/*/*/.debug/"
FILES_${PN} += "${libdir}/enlightenment/modules/"
