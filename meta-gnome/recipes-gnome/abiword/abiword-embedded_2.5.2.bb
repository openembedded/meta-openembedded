require abiword-2.5.inc

PR = "r1"

EXTRA_OECONF += "--enable-embedded"

S = "${WORKDIR}/abiword-${PV}"

RCONFLICTS = "abiword"
RPROVIDES += "abiword"


