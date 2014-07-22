require ${BPN}.inc

SRCREV = "c1497e10288589ff9834f3d4da17461b8a3a1c15"
PV = "1.10.0+git${SRCPV}"

SRC_URI = "git://git.enlightenment.org/bindings/python/${BPN}.git;branch=python-efl-1.10"

S = "${WORKDIR}/git"
