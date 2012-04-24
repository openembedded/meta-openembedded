DESCRIPTION = "Eeze is a library to simplify the use of devices"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=315521fe061b6fd4290ef01db714a3c8"
DEPENDS = "ecore eet udev"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/enlightenment/utils/eeze_scanner"
FILES_${PN}-dbg += "${libdir}/enlightenment/utils/.debug"

RRECOMMENDS_${PN} += "eject"
