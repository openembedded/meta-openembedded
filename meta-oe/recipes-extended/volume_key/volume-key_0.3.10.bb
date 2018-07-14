DESCRIPTION = "The volume_key project provides a libvolume_key, a library for manipulating \
storage volume encryption keys and storing them separately from volumes, and an \
associated command-line tool, named volume_key."
LICENSE = "GPLv2"
SECTION = "devel/lib"

HOMEPAGE = "https://pagure.io/volume_key"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://releases.pagure.org/volume_key/volume_key-${PV}.tar.xz \
           file://0001-explicitly-support-python3-by-pkg-config.patch \
"
SRC_URI[md5sum] = "605fd99a6e42916728020562a6edee78"
SRC_URI[sha256sum] = "c5729de7e33e39c8674e9aae2337d2719f9298d6499f1656d0d25a065a7e98a7"

SRCNAME = "volume_key"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit autotools python3native gettext

DEPENDS += " \
    util-linux \
    glib-2.0 \
    cryptsetup \
    nss \
    gpgme \
    swig-native \
"

RDEPENDS_python3-${PN} += "${PN}"

PACKAGES += "python3-${PN}"
FILES_python3-${PN} = "${PYTHON_SITEPACKAGES_DIR}/*"

