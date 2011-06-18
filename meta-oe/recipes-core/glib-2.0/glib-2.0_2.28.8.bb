require glib.inc

PR = "r1"
PE = "1"

SRC_URI = "${GNOME_MIRROR}/glib/2.28/glib-${PV}.tar.bz2 \
           file://configure-libtool.patch \
           file://60_wait-longer-for-threads-to-die.patch \
           file://g_once_init_enter.patch \
           file://0003-gatomic-proper-pointer-get-cast.patch.patch \
           file://0005-glib-mkenums-interpreter.patch.patch \
          "
# Only apply this patch for target recipe on uclibc
SRC_URI_append_libc-uclibc = " ${@['', 'file://no-iconv.patch']['${PN}' == '${BPN}']}"

SRC_URI_append_virtclass-native = " file://glib-gettextize-dir.patch"

SRC_URI[md5sum] = "789e7520f71c6a4bf08bc683ec764d24"
SRC_URI[sha256sum] = "222f3055d6c413417b50901008c654865e5a311c73f0ae918b0a9978d1f9466f"

BBCLASSEXTEND = "native"

FILES_${PN} += "${sysconfdir}/bash_completion.d ${datadir}/glib-2.0/schemas"
FILES_${PN}-dbg += "${datadir}/glib-2.0/gdb ${datadir}/gdb"

