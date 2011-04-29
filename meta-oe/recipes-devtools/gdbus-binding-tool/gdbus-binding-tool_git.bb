DESCRIPTION = "gdbus-binding-tool is used to generate C code for interacting with remote objects using D-Bus."
DEPENDS = "glib-2.0 libffi python-argparse gdbus-binding-tool-native"
DEPENDS_virtclass-native = "glib-2.0-native libffi-native python-argparse-native"
RDEPENDS_${PN} = "glib-2.0-utils"
# taken from glib where this is supposed to be moved later
LICENSE = "LGPLv2+ & BSD & public domain"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

PR = "r2"

inherit autotools pkgconfig

SRC_URI = "git://anongit.freedesktop.org/~david/${BPN};protocol=git;branch=master \
           file://COPYING"
SRCREV = "286f4eaf48ea79821be5a4e6bf493566b156faa4"
PV = "0.1+gitr${SRCPV}"
S = "${WORKDIR}/git"

do_configure() {
  # missing ${topdir}/gtk-doc.make and --disable-gtk-doc* is not enough
  sed -i '/^doc\/Makefile/d' ${S}/configure.ac
  sed -i 's/SUBDIRS = src doc/SUBDIRS = src/g' ${S}/Makefile.am

  # cannot execute target binary, so use staged native
  sed -i "s#\$(top_builddir)/src/gdbus-codegen#${STAGING_BINDIR_NATIVE}/gdbus-codegen#g" ${S}/src/Makefile.am

  autotools_do_configure
}
do_configure_virtclass-native() {
  # missing ${topdir}/gtk-doc.make and --disable-gtk-doc* is not enough
  sed -i '/^doc\/Makefile/d' ${S}/configure.ac
  sed -i 's/SUBDIRS = src doc/SUBDIRS = src/g' ${S}/Makefile.am

  autotools_do_configure
}

BBCLASSEXTEND = "native"
