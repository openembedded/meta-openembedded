require ${BPN}.inc

PV = "0.19.2+git${SRCPV}"
DEFAULT_PREFERENCE = "-2"

SRC_URI = " \
    git://git.enlightenment.org/core/enlightenment.git;branch=enlightenment-0.19 \
    file://enlightenment_start.oe \
    file://applications.menu \
"
S = "${WORKDIR}/git"

SRCREV = "be0d07877e561edd7242368797a90c36c4e77ee4"
