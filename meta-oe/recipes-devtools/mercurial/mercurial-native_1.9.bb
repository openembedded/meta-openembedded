DESCRIPTION = "The Mercurial distributed SCM"
HOMEPAGE = "http://mercurial.selenic.com/"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "python-native"
PR = "r1"

SRC_URI = "http://mercurial.selenic.com/release/mercurial-${PV}.tar.gz"
SRC_URI[md5sum] = "d4842129fa2732eb6ed1180467bc32e2"
SRC_URI[sha256sum] = "711e4b1cd2924a7a88499d6c431a9122390183bf554d4e153edbb3b2f30123e0"

S = "${WORKDIR}/mercurial-${PV}"

inherit native

EXTRA_OEMAKE = "STAGING_LIBDIR=${STAGING_LIBDIR} STAGING_INCDIR=${STAGING_INCDIR} \
        BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} PREFIX=${prefix}"

do_install () {
        oe_runmake -e install-bin DESTDIR=${D} PREFIX=${prefix}
}

NATIVE_INSTALL_WORKS = "1"
