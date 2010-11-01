SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Low-level pixel manipulation library."
LICENSE = "X11"

DEPENDS = "perl-native"

BBCLASSEXTEND = "native"

SRC_URI = "http://cairographics.org/releases/${BPN}-${PV}.tar.gz;name=archive "

INC_PR = "r5"

inherit autotools pkgconfig
