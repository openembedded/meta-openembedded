require qt-${PV}.inc
require qt4-x11-free.inc

PR = "${INC_PR}.0"

QT_CONFIG_FLAGS += " -xrandr "
