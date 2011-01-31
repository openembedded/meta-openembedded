require qt-${PV}.inc
require qt4-embedded.inc

PR = "${INC_PR}.1"

QT_CONFIG_FLAGS_append_armv6 = " -no-neon "

QT_CONFIG_FLAGS += " \
 -exceptions \
"
