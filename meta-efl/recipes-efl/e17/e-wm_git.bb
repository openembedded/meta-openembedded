require ${BPN}.inc

PV = "0.19.3+git${SRCPV}"
DEFAULT_PREFERENCE = "-2"

SRC_URI = " \
    git://git.enlightenment.org/core/enlightenment.git;branch=enlightenment-0.19 \
    file://0001-configure.ac-add-foreign.patch \
    file://enlightenment_start.oe \
    file://applications.menu \
"
S = "${WORKDIR}/git"

SRCREV = "19e59b6e8148388e7a17f10d4390176a50e2917f"
