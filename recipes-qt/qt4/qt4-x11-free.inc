DESCRIPTION = "Qt is a versatile cross-platform application framework -- this is the X11 version."
SECTION = "x11/libs"
PRIORITY = "optional"
HOMEPAGE = "http://www.trolltech.com"
DEPENDS += "virtual/libx11 fontconfig libxft libxext libxrender libxrandr libxcursor"

INC_PR = "r21"

SRC_URI = "ftp://ftp.trolltech.com/qt/source/qt-x11-opensource-src-${PV}.tar.gz \
           file://0001-cross-compile.patch \
           file://0002-fix-resinit-declaration.patch \
           file://0003-no-tools.patch \
           file://0004-no-qmake.patch \
           file://0006-freetype-host-includes.patch \
           file://0007-openssl-host-includes.patch \ 
           file://0008-qt-lib-infix.patch \
           file://fix-config-tests.patch \
           file://g++.conf \
           file://linux.conf \
           "
S = "${WORKDIR}/qt-x11-opensource-src-${PV}"

QT_GLFLAGS ?= "-no-opengl"
QT_GLFLAGS_qemux86 = "-opengl"
QT_GLFLAGS_emenlow = "-opengl"
QT_GLFLAGS_atom-pc = "-opengl"
QT_CONFIG_FLAGS += "-no-xinerama -no-xkb ${QT_GLFLAGS}"
QT_BASE_NAME ?= "qt4"
QT_BASE_LIB  ?= "libqt"
QT_DIR_NAME = "qt4"
QT_LIBINFIX = ""

require qt4.inc

inherit qt4x11

