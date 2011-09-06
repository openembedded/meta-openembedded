DESCRIPTION = "AbiWord is free word processing program similar to Microsoft(r) Word"
SECTION = "gnome/office"
HOMEPAGE = "http://www.abiword.org""
LICENSE = "GPLv2"

DEPENDS     = "libgsf libglade libfribidi libxml2 wv"
RRECOMMENDS_${PN}    = "glibc-gconv-ibm850 glibc-gconv-cp1252 \
               glibc-gconv-iso8859-15 glibc-gconv-iso8859-1"

# Share this file between recipes that use cvs and that pull tarballs
# (one day change this to differentiate automatically based on PV and set the
# SRC_URI and S var directly)
RELURI = "http://www.abiword.org/downloads/abiword/${PV}/source/abiword-${PV}.tar.gz"
RELSRC = "${WORKDIR}/abiword-${PV}/abi"

CVSURI = "cvs://anoncvs:anoncvs@anoncvs.abisource.com/cvsroot;module=abi"
CVSSRC = "${WORKDIR}/abi"

#want 2.x from 2.x.y for the installation directory
SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"

FILES_${PN} += " \
		${datadir}/icons/* \
		${datadir}/mime-info \
		${datadir}/abiword-${SHRT_VER}/glade \
		${datadir}/abiword-${SHRT_VER}/system.profile-en \
		${datadir}/abiword-${SHRT_VER}/system.profile-en-GB \
  "

#		${datadir}/abiword-${SHRT_VER}/templates/normal.awt \
#		${datadir}/abiword-${SHRT_VER}/templates/normal.awt-en_GB \

inherit autotools pkgconfig

PARALLEL_MAKE = ""

# This is a minimalistic AbiWord build
#
# if you are planning to build plugins, make sure to comment out the
# --disable-exports options

EXTRA_OECONF = " --enable-embedded=poky    \
		 --disable-printing   \
		 --with-sys-wv 	     \
		 --disable-exports    \
		 --disable-spellcheck"

# TODO -- refactor this, so that instead of two strings and profiles
# packages we have lots of locale packages, each with a set of strings and
# a profile
#
PACKAGES += " abiword-strings abiword-systemprofiles"

FILES_abiword-strings 		+= "${datadir}/abiword-${SHRT_VER}/strings"
FILES_abiword-systemprofiles 	+= "${datadir}/abiword-${SHRT_VER}/system.profile*"

# AbiWord configure.ac does not play nicely with autoreconf
# so use the autogen.sh script that comes with AbiWord
#
do_configure() {
	cd ${S}
	export NOCONFIGURE="no"; ./autogen.sh
	oe_runconf
}
