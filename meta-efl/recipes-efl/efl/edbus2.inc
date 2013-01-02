DESCRIPTION = "DBus convenience wrappers for EFL"
DEPENDS = "dbus ecore eina"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ed65f801204783f71fedafc082038d9"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

SRCNAME = "edbus"
