require glib.inc

PE = "1"
PR = "r1"

DEFAULT_PREFERENCE = "-1"

DEPENDS += "libffi python-argparse-native"
DEPENDS_virtclass-native += "libffi-native python-argparse-native"

SRC_URI = "git://git.gnome.org/glib;protocol=git;branch=master \
           file://configure-libtool.patch \
           file://60_wait-longer-for-threads-to-die.patch \
           file://g_once_init_enter.patch \
           file://remove.test.for.qsort_r.patch \
          "
SRCREV = "d97cbc6731deab137770bc0fe9c69b06f689f5b4"
PV = "2.29.3+gitr${SRCPV}"
S = "${WORKDIR}/git"

# Only apply this patch for target recipe on uclibc
SRC_URI_append_libc-uclibc = " ${@['', 'file://no-iconv.patch']['${PN}' == '${BPN}']}"

SRC_URI_append_virtclass-native = " file://glib-gettextize-dir.patch"
BBCLASSEXTEND = "native"

do_configure_prepend() {
  # missing ${topdir}/gtk-doc.make and --disable-gtk-doc* is not enough, because it calls gtkdocize (not provided by gtk-doc-native)
  sed -i '/^docs/d' ${S}/configure.ac
  sed -i 's/SUBDIRS = . m4macros glib gmodule gthread gobject gio tests po docs/SUBDIRS = . m4macros glib gmodule gthread gobject gio tests po/g' ${S}/Makefile.am
  sed -i -e "s:TEST_PROGS += gdbus-serialization::g"  ${S}/gio/tests/Makefile.am
}
