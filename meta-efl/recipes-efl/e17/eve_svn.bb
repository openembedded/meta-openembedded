DESCRIPTION = " Enlightenment Web Browser"
LICENSE = "GPL"
DEPENDS = "evas ecore edje elementary webkit-efl"
PV = "0.3.0.0+svnr${SRCPV}"
PR = "r1"
SRCREV = "${EFL_SRCREV}"

inherit e
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

SRC_URI += "file://eve-theme-for-smaller-screens.patch \
            file://0001-eve-remove-unconditional-ewk_view_context_menu_forwa.patch"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

FILES_${PN} += "\
        ${datadir}/icons/eve.png \
"
