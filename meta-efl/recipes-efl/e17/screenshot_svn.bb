LICENSE = "MIT"
PV = "0.3.0+svnr${SRCREV}"
PR = "${INC_PR}.0"

require e-module.inc

DEPENDS += "emprint"
RDEPENDS_${PN} += "emprint"

do_configure_prepend() {
	sed -i -e 's:AC_MSG_ERROR(emprint not found):echo foo:g' ${S}/configure.ac
}


