SUMMARY = "The Mercurial distributed SCM"
HOMEPAGE = "http://mercurial.selenic.com/"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "python-native"

SRC_URI = "http://mercurial.selenic.com/release/mercurial-${PV}.tar.gz"
SRC_URI[md5sum] = "15de301a673b77f839325dba10ed4fc0"
SRC_URI[sha256sum] = "36e48b59a84ef5a222d06596971e955ac8217e56b076dfb94c8ce5a0c29fd705"

S = "${WORKDIR}/mercurial-${PV}"

inherit native

EXTRA_OEMAKE = "STAGING_LIBDIR=${STAGING_LIBDIR} STAGING_INCDIR=${STAGING_INCDIR} \
    BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} PREFIX=${prefix}"

do_configure_append () {
    sed -i -e 's:PYTHON=python:PYTHON=${STAGING_BINDIR_NATIVE}/python-native/python:g' ${S}/Makefile
}

do_install () {
    oe_runmake -e install-bin DESTDIR=${D} PREFIX=${prefix}
}

