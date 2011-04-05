LICENSE = "MIT CC-BY-SA2.5"
PV = "0.1.0+svnr${SRCREV}"
PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=df45d9de58df0b48f515eae8d2421257"
LIC_FILES_CHKSUM = "file://COPYING.icons;md5=d4e4f10748f3146a089aaa23c9ade59b"

require e-module.inc

inherit gettext

DEPENDS += "elementary ethumb ecore eio"

SRCNAME = "${PN}"
