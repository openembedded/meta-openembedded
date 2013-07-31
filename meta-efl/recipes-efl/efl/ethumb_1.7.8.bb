DESCRIPTION = "EFL based thumbnail generation library"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a6db9e80255adbafa16e817d9a4d8c"
DEPENDS = "libexif eet-native evas ecore edje eet edbus emotion epdf"

inherit efl

EXTRA_OECONF = "\
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
    --disable-docs \
"

# Some upgrade path tweaking, as in evas
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "\
    ${bindir}/ethumbd \
    ${libexecdir}/ethumbd_slave \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "9c5936d06ff79aea0dd81c5be0bf511e"
SRC_URI[sha256sum] = "9047a88122c8713444d66294b3343c07feb15817a1809c7cbc9478ba91587e2d"
