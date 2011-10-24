DESCRIPTION = "Enca is an Extremely Naive Charset Analyser"
SECTION = "libs"
HOMEPAGE = "http://trific.ath.cx/software/enca/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=24b9569831c46d4818450b55282476b4"

SRC_URI = "http://www.sourcefiles.org/Networking/Tools/Miscellanenous/enca-${PV}.tar.bz2 \
	file://configure-hack.patch \
	file://dont-run-tests.patch \
	file://configure-remove-dumbness.patch \
	file://makefile-remove-tools.patch "

SRC_URI[md5sum] = "b3581e28d68d452286fb0bfe58bed3b3"
SRC_URI[sha256sum] = "02acfef2b24a9c842612da49338138311f909f1cd33933520c07b8b26c410f4d"

inherit autotools

EXTRA_OECONF = "--with-libiconv-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

do_configure_prepend() {
	# remove failing test which checks for something that isn't even used
	sed -i -e '/ye_FUNC_SCANF_MODIF_SIZE_T/d' configure.ac
}

do_configure_append() {
	sed -i s:-I/usr/include::g Makefile
	sed -i s:-I/usr/include::g */Makefile
}

do_compile() {
    cd ${S}/tools && ${BUILD_CC} -o make_hash make_hash.c
    cd ..
    oe_runmake
}

