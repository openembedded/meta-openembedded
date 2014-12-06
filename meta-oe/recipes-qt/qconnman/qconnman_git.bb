require qconnman.inc

RCONFLICTS_${PN} = "qconnman-e"

inherit qt4x11

PNBLACKLIST[qconnman] ?= "BROKEN: doesn't work with B!=S, ls: cannot access *.pro: No such file or directory"

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
