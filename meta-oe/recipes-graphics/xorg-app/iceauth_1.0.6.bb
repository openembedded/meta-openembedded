require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "X.Org X11 X client utilities"

DESCRIPTION = "A collection of utilities used to tweak and query the runtime configuration\
of the X server."

LIC_FILES_CHKSUM = "file://COPYING;md5=13f70acf3c27f5f834bbc954df775f8e"

BBCLASSEXTEND = "native"

DEPENDS += "libice"

SRC_URI[md5sum] = "2527344acc60741a709f4858564c5ae6"
SRC_URI[sha256sum] = "bd990837353b439e6f45d478a87b8dbfa3f67d72d903e7a9ed4eb8de52f2e2f4"
