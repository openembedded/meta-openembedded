SUMMARY = "Mobile device API extensions for Qt/X11 4.x"
DEPENDS = "libxv libxext"
SECTION = "x11/libs"
qtm_embedded := ""
qtm_dir = "qt4"
qtm_glflags := "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '+=opengl', '-=opengl', d)} "
qtm_extra_config := ""

inherit qt4x11
require qt-mobility_${PV}.inc
