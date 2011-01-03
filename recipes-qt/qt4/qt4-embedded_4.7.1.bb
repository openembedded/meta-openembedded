DEFAULT_PREFERENCE = "-1"

require qt4-embedded.inc

PR = "${INC_PR}.0"

QT_CONFIG_FLAGS_append_armv6 = " -no-neon "

require qt-${PV}.inc

QT_CONFIG_FLAGS += " \
 -exceptions \
"

