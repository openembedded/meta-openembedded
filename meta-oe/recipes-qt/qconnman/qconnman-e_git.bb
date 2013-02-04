require qconnman.inc

RCONFLICTS_${PN} = "qconnman"

inherit qt4e

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

FILES_${PN} += "${datadir}/qconnman/"
