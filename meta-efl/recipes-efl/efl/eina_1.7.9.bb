DESCRIPTION = "Eina is the Enlightenment data library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9cc092d35d7bbfcd986232cde130a551"

BBCLASSEXTEND = "native"

inherit efl

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/eina"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "cddf2755e20bfb3f90764fd2b689b888"
SRC_URI[sha256sum] = "b7fe26d98b80479d0f72be47b2584dfa243e4d18774cb824acc398354a45d520"
