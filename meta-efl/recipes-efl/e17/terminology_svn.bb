DESCRIPTION = "Enlightenment Terminal Emulator"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=03f6c2cf2bef233fadf7d0769f5bfda7"
DEPENDS = "evas ecore edje elementary eina eet emotion"

PV = "0.1.0+svnr${SRCPV}"
PR = "r1"
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

# doesn't start without own theme
# ERR<770>:terminology utils.c:26 theme_apply() Could not load any theme for group=terminology/background: No Error
# CRI<770>:terminology main.c:448 elm_main() Couldn't find terminology theme! Forgot 'make install'?
RDEPENDS_${PN} += "${PN}-themes"
