require qconnman.inc

RCONFLICTS_${PN} = "qconnman-e"

inherit qt4x11

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
