require qt4-x11-free.inc
require qt-${PV}.inc

PR = "${INC_PR}.1"

QT_CONFIG_FLAGS_append_armv6 = " -no-neon "

QT_CONFIG_FLAGS += " \
 -no-embedded \
 -xrandr \
 -x11"

