DESCRIPTION = "Eeze is a library to simplify the use of devices"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=5388f2e926064f899c2481629ec3de18"
DEPENDS = "ecore eet udev"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r1"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/enlightenment/utils/eeze_scanner"
