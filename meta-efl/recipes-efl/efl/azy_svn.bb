DESCRIPTION = "Azy is a library meant for implementing rpc clients and servers in a simple manner."
DEPENDS = "pkgconfig zlib openssl eina gnutls ecore libxml2 re2c-native mysql5 azy-native"
DEPENDS_virtclass-native = "pkgconfig-native zlib-native openssl-native eina-native gnutls-native ecore-native libxml2-native re2c-native mysql5-native"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

# to provide native lemon binary
BBCLASSEXTEND = "native"

EXTRA_OECONF += " --disable-mysql-tests"

do_configure_class-target() {
  sed -i "s#\./lemon#${STAGING_BINDIR_NATIVE}/lemon#g" ${S}/src/bin/Makefile.am
  sed -i "s#\$(top_builddir)/src/bin/azy_parser -H -p -o#${STAGING_BINDIR_NATIVE}/azy_parser -H -p -o#g" ${S}/src/tests/Makefile.am
  sed -i "s#\$(top_builddir)/src/bin/azy_parser -eHn -m#${STAGING_BINDIR_NATIVE}/azy_parser -eHn -m#g" ${S}/src/tests/identi.ca/Makefile.am
  efl_do_configure
}
do_configure_class-native() {
  efl_do_configure
}

do_install_append_class-native() {
  install -d ${D}/${bindir}
  install -m 0755 ${S}/src/bin/lemon ${D}/${bindir}
}

inherit efl

SRC_URI = "${E_SVN}/trunk/PROTO;module=${SRCNAME};protocol=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

