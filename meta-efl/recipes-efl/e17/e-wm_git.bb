require ${BPN}.inc

PV = "0.18.999+0.19-alpha2+git${SRCPV}"
DEFAULT_PREFERENCE = "-1"

SRC_URI = " \
    git://git.enlightenment.org/core/enlightenment.git \
    file://0001-configure.ac-add-foreign.patch \
    file://enlightenment_start.oe \
    file://applications.menu \
"
S = "${WORKDIR}/git"

SRCREV = "525b963133d7168226b8e07ba26730212a8b7e82"
