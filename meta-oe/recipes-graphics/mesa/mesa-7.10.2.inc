SRC_URI = "ftp://ftp.freedesktop.org/pub/mesa/${PV}/MesaLib-${PV}.tar.bz2;name=archive \
           file://glamo.patch \
           file://uclibc.patch \
          "

DEPENDS += "talloc"

SRC_URI[archive.md5sum] = "f5de82852f1243f42cc004039e10b771"
SRC_URI[archive.sha256sum] = "8ced2678ce11cf30804694a92ea3ca6b82f158ae8995bdc626c7e85aac71c7c1"

EXTRA_OECONF += " --disable-gallium"

#needs more testing and updated glamo.patch before making default
DEFAULT_PREFERENCE = "-2"

do_configure_prepend() {
  #check for python not python2, because python-native does not stage python2 binary/link
  sed -i 's/AC_CHECK_PROGS(\[PYTHON2\], \[python2 python\])/AC_CHECK_PROGS(\[PYTHON2\], \[python python\])/g' ${S}/configure.ac
}
