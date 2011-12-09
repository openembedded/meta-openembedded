require qconnman.inc

RCONFLICTS = "qconnman"

inherit qt4e

EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
