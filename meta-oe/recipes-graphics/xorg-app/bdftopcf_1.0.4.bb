require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "bdftopcf is a tool to convert BDF fonts to PCF fonts"
HOMEPAGE = "http://cgit.freedesktop.org/xorg/app/bdftopcf/"
SECTION = "x11/app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=f9a35333adf75edd1eaef84bca65a490"
DEPENDS = "libxfont"

SRC_URI[md5sum] = "96a648a332160a7482885800f7a506fa"
SRC_URI[sha256sum] = "eaf59057ba3d7cffe29526562ce50868da7da823487a4cfb3e16946e5ffd2798"

BBCLASSEXTEND = "native"
