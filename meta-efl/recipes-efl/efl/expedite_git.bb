require ${BPN}.inc

SRCREV = "a5e6af917af52877b378090811cf836c16d0bfbb"
PV = "1.7.99+gitr${SRCPV}"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
    git://git.enlightenment.org/tools/${BPN}.git \
"
S = "${WORKDIR}/${SRCNAME}"

PNBLACKLIST[expedite] ?= "Depends on blacklisted eet"

PNBLACKLIST[expedite] ?= "Depends on blacklisted evas"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted evas-loader-png"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted expedite"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted evas-engine-software-generic"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted evas-engine-fb"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted evas-engine-software-x11"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted evas-engine-gl-x11"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted expedite-dev"

PNBLACKLIST[expedite] ?= "Runtime depends on blacklisted expedite-themes"
