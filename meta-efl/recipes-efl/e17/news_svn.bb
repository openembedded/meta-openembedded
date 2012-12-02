LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PV = "0.1.0+svnr${SRCPV}"
PR = "${INC_PR}.0"

require e-module.inc

do_configure_prepend() {
    sed -i -e /po/d configure.ac 
    sed -i -e s:\ po::g Makefile.am
}
