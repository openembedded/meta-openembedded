DESCRIPTION="Xfce Menu Library"
SECTION = "x11/libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=04a01abcbdabffae1ca26335a293276b"
DEPENDS = "glib-2.0 xfce4-dev-tools-native libxfce4util"

PR = "r0"

inherit xfce

FILES_${PN} += " ${datadir}/desktop-directories"

SRC_URI[md5sum] = "a3ca1e54ad731c98f688900f6398fc20"
SRC_URI[sha256sum] = "485e23c8ec1af0d3af423aa244e05467dd2f96afbb66efc70ca7689222cf31d5"
