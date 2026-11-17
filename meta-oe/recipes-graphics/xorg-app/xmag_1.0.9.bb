require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "xmag is a tool to magnify parts of the screen"
HOMEPAGE = "http://cgit.freedesktop.org/xorg/app/xmag/"
SECTION = "x11/app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3413fe6832380b44b69b172d2d1b2387"
DEPENDS += " libxaw libxt"

SRC_URI_EXT = "xz"
SRC_URI[sha256sum] = "87d82c0c08c04ef1a9988705a3506295650777b26deae97ada96a1720982c024"
