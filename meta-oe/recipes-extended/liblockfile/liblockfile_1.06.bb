DESCRIPTION = "File locking library."
SECTION = "libs"
LICENSE = "LGPLv2+ & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=35127b30003a121544b5d13a2ac868b6"

SRC_URI = "http://snapshot.debian.org/archive/debian/20050312T000000Z/pool/main/libl/liblockfile/liblockfile_${PV}.tar.gz \
	   file://install.patch \
	   file://configure.patch \
	   file://ldflags.patch \
	   file://glibc-2.4.patch"

inherit autotools

EXTRA_OECONF = "--enable-shared --enable-static"

do_install () {
	oe_runmake 'ROOT=${D}' INSTGRP='' install
}

SRC_URI[md5sum] = "2de88389da013488bfd31356523070c0"
SRC_URI[sha256sum] = "14f9690328318d11f9ba13a9356a2c008bdd169b7a817f38cb7f9eb32cf7240e"
