SUMMARY = "Send desktop notifications to a notification daemon"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "dbus gtk+3"

# conflicts with libnotify, mixing them in build breaks couple of packages
EXCLUDE_FROM_WORLD = "1"

BPN = "libnotify"

inherit gnome lib_package

SRC_URI[archive.md5sum] = "d20676bde7bd2d276508d019dc7276f1"
SRC_URI[archive.sha256sum] = "322541fdf276eb803686df81763a5186af041b2ee3e9cc5fa1dc86c550388c88"
