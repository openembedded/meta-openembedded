require qconnman.inc

RCONFLICTS_${PN} = "qconnman"

inherit qt4e

PNBLACKLIST[qconnman-e] ?= "BROKEN: doesn't work with B!=S, ls: cannot access *.pro: No such file or directory"

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

FILES_${PN} += "${datadir}/qconnman/"
