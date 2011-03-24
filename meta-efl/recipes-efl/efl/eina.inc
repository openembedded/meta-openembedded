DESCRIPTION = "Eina is the Enlightenment data library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=723bb07e0e90220fe8f03924969e5f4e"

BBCLASSEXTEND = "native"
INC_PR = "r0"

inherit efl

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libeina-ver-pre-svn-00-0 libeina-ver-pre-svn-01-0"

FILES_${PN} += "${libdir}/eina"
