DESCRIPTION = "A lightweight Terminal Emulator based on libvte, written in Vala."
SECTION = "x11/applications"
DEPENDS = "vala-native vte"
SRCREV = "0fefa38087581f85fa0631b40500b9428369c146"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
PV = "1.3+gitr${SRCPV}"
PE = "1"

inherit autotools perlnative

SRC_URI = "${FREESMARTPHONE_GIT}/vala-terminal.git;protocol=git;branch=master"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "ttf-liberation-mono"
RREPLACES_${PN} = "openmoko-terminal2"
RPROVIDES_${PN} = "openmoko-terminal2"
