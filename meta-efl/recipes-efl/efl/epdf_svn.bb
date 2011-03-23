DESCRIPTION = "Epdf is the glue between EFL and libpoppler"
LICENSE = "MIT BSD"
DEPENDS = "poppler evas ecore"
PV = "0.1.0+svnr${SRCPV}"
PR = "r4"
SRCREV = "${EFL_SRCREV}"

inherit efl
SRC_URI = "${E_SVN}/trunk/PROTO;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "\
    --enable-poppler \
    --disable-ewl \
    --disable-mupdf \
"

# Some upgrade path tweaking, as in evas
AUTO_LIBNAME_PKGS = ""

