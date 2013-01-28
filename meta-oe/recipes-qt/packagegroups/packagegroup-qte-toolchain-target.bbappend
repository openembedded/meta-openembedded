RDEPENDS_${PN} += "qwt-e-dev"

# qwt-e conflicts with qwt, so only one can be built in world and this pulls qwt-e
EXCLUDE_FROM_WORLD = "1"

PRINC := "${@int(PRINC) + 1}"
