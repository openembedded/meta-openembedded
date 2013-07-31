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

SRC_URI[md5sum] = "4c7715b636f71a5e07be4f33e72c6a69"
SRC_URI[sha256sum] = "e39c5d9cc9d0854521983fe653b8f2efe2946e74066c7c0456ffe62342535d18"
