require abiword-2.5.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/abiword-${SHRT_VER}:"
PR = "r3"

RCONFLICTS_${PN} = "abiword-embedded"
