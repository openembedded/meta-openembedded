require xorg-app-common.inc
DEPENDS += " xproto util-macros"
LIC_FILES_CHKSUM = "file://COPYING;md5=ef598adbe241bd0b0b9113831f6e249a"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "35c6cccbf25a872bdd62bfcb1a73d951"
SRC_URI[sha256sum] = "80887da011ad086fff88bfd16c6d9d5ac7da45ef1bc9d0c192a6f370423370f1"

FILES_${PN} += "${datadir}/X11"
