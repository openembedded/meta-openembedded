DESCRIPTION="Xfce Menu Library"
SECTION = "x11/libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=04a01abcbdabffae1ca26335a293276b"
DEPENDS = "glib-2.0 xfce4-dev-tools"

PR = "r0"

inherit xfce

FILES_${PN} += " ${datadir}/desktop-directories"

SRC_URI[md5sum] = "18fbf523ed2865dfaccdfb40b4b20b05"
SRC_URI[sha256sum] = "955a05e72ebbadc8207bb7b2c7349e71830dbdd596dbba3ea3665f0ecfb9cf63"
