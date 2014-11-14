require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "X.Org X11 X client utilities"
HOMEPAGE = "http://cgit.freedesktop.org/xorg/app/xlsatoms/"
DESCRIPTION = "Xlsatoms lists the interned atoms defined on an X11 server"
SECTION = "x11/app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b08d9e2e718ac83e6fe2b974d4b5fd8"

DEPENDS += "libxmu"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "9d0e16d116d1c89e6b668c1b2672eb57"
SRC_URI[sha256sum] = "3b8bd98c1ca9789178db80bca75556b0c76e5092403014995d5ddaa3117536f9"
