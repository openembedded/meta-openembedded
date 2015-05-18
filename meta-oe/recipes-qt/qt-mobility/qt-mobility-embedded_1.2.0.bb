SUMMARY = "Mobile device API extensions for Qt/Embedded 4.x"
DEPENDS = "qt4-embedded"
SECTION = "libs"
qtm_embedded := "embedded"
qtm_dir = "qtopia"
qtm_glflags := "-=opengl"
qtm_extra_config := "gstreamer-photography_enabled = no"

inherit qt4e
require qt-mobility_${PV}.inc

