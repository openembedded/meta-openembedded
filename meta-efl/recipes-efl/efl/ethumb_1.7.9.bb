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

SRC_URI[md5sum] = "d71a6fbcce2c6a8f46ea9b9b9d53cbab"
SRC_URI[sha256sum] = "a97c39a9184ec7e1b352a7f251d97992b8082e3a0d6735ea1f34e4e40129a12f"
