require qt4.inc

DESCRIPTION = "Qt is a versatile cross-platform application framework -- this is the X11 version."
HOMEPAGE = "http://qt.nokia.com"
SECTION = "x11/libs"
PRIORITY = "optional"
DEPENDS += "virtual/libx11 fontconfig libxft libxext libxrender libxrandr libxcursor"

INC_PR = "r21"

QT_GLFLAGS ?= "-no-opengl"
QT_GLFLAGS_qemux86 = "-opengl"
QT_GLFLAGS_emenlow = "-opengl"
QT_GLFLAGS_atom-pc = "-opengl"
QT_CONFIG_FLAGS += "-no-xinerama -no-xkb ${QT_GLFLAGS}"
QT_BASE_NAME ?= "qt4"
QT_BASE_LIB  ?= "libqt"

inherit qt4x11

