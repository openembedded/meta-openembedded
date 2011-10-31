require python-efl.inc
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
PR = "${INC_PR}.1"
SRCREV = "${EFL_SRCREV}"
DEPENDS += "edbus python-dbus"
RDEPENDS_${PN} += "python-dbus"
SRCNAME = "python-e_dbus"
