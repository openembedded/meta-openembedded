DESCRIPTION = "Eina is the Enlightenment data library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9cc092d35d7bbfcd986232cde130a551"

BBCLASSEXTEND = "native"
INC_PR = "r0"

inherit efl

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/eina"

do_install_append() {
  rmdir ${D}${bindir}
}
