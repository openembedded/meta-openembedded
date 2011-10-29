DESCRIPTION = "Login manager for Enlightenment"
DEPENDS = "efreet eina eet ecore elementary"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "e/apps"

inherit e gettext
SRC_URI = "${E_SVN}/trunk/PROTO;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "--with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc"

PV = "0.0.4+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

RDEPENDS_${PN} = "${PN}-themes"
