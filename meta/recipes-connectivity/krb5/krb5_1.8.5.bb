DESCRIPTION = "A network authentication protocol"
HOMEPAGE = "http://web.mit.edu/Kerberos/"
SECTION = "console/network"
PR = "r0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/../README;md5=5595a75ae6fab5f825f579a817baa618"
DEPENDS = "ncurses util-linux e2fsprogs-native"

inherit autotools binconfig perlnative

SRC_URI = "http://web.mit.edu/kerberos/dist/krb5/1.8/krb5-1.8.5-signed.tar"

S = "${WORKDIR}/${PN}-${PV}/src/"

EXTRA_OECONF += " --without-tcl --with-system-et"
CACHED_CONFIGUREVARS += "krb5_cv_attr_constructor_destructor=yes ac_cv_func_regcomp=yes \
                  ac_cv_printf_positional=yes ac_cv_file__etc_environment=yes \
                  ac_cv_file__etc_TIMEZONE=no"

CFLAGS_append += "-DDESTRUCTOR_ATTR_WORKS=1 -I${STAGING_INCDIR}/et"
LDFLAGS_append += "-lpthread"

FILES_${PN}-doc += ${datadir}/examples

krb5_do_unpack() {
	tar xzf ${WORKDIR}/krb5-1.8.5.tar.gz -C ${WORKDIR}/
}

python do_unpack() {
	bb.build.exec_func('base_do_unpack', d)
	bb.build.exec_func('krb5_do_unpack', d)
}

do_configure() {
	oe_runconf
}

SRC_URI[md5sum] = "48b17caedf19d07d714a8d7a25422c95"
SRC_URI[sha256sum] = "7d6989efaaf0b3330ce5b329edb1d0bf33250ca78b789fdd117e02c49c238812"
