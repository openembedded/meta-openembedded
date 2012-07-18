DESCRIPTION = "Enlightenment Terminal Emulator"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=03f6c2cf2bef233fadf7d0769f5bfda7"
DEPENDS = "evas ecore edje elementary eina eet emotion"

PV = "0.1.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e gettext
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};protocol=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

FILES_${PN} += "\
        ${datadir}/icons/terminology.png \
"
