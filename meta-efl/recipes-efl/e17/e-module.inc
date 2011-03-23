DESCRIPTION = "E17 ${PN} module"
SECTION = "x11/multimedia"
DEPENDS = "e-wm"

SRCREV = "${EFL_SRCREV}"
INC_PR = "r5"

SRCNAME = "E-MODULES-EXTRA/${PN}"

inherit e
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "--with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc"

FILES_${PN} += "${libdir}/enlightenment/modules/${PN}"
FILES_${PN}-dbg += "${libdir}/enlightenment/modules/${PN}/*/.debug"

