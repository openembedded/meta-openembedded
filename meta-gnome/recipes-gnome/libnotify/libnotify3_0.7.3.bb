DESCRIPTION = "Send desktop notifications to a notification daemon"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "dbus gtk+3"

BPN = "libnotify"

inherit gnome lib_package

