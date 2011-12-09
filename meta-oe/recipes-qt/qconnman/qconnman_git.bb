require qconnman.inc

RCONFLICTS = "qconnman-e"

inherit qt4x11

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
