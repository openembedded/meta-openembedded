DESCRIPTION = " Enlightenment Web Browser"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"
DEPENDS = "evas ecore edje elementary webkit-efl"
PV = "0.3.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e gettext
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

do_configure_prepend() {
       autopoint || touch config.rpath
}

SRC_URI += "file://eve-theme-for-smaller-screens.patch \
"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

FILES_${PN} += "\
        ${datadir}/icons/eve.png \
"
