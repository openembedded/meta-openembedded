require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "X.Org X11 X client utilities"

DESCRIPTION = "xgamma allows X users to query and alter the gamma \
correction of a monitor via the X video mode extension."

LIC_FILES_CHKSUM = "file://COPYING;md5=ac9801b8423fd7a7699ccbd45cf134d8"

DEPENDS += "libxxf86vm"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "c06067f572bc4a5298f324f27340da95"
SRC_URI[sha256sum] = "e322a2fea80d559c09d6bc285ebe1a9e454dbeae2a07116cb7d2207db9d2c310"
