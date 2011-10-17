require abiword-2.5.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/abiword-${SHRT_VER}:"
PR = "r2"

EXTRA_OECONF += "--enable-embedded"

S = "${WORKDIR}/abiword-${PV}"

RCONFLICTS = "abiword"
RPROVIDES += "abiword"


