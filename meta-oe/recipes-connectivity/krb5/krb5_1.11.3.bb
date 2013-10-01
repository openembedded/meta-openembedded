DESCRIPTION = "A network authentication protocol"
HOMEPAGE = "http://web.mit.edu/Kerberos/"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/../NOTICE;md5=1d9c8180d79838c02eb5eb1a3b184eb9"
DEPENDS = "ncurses util-linux e2fsprogs e2fsprogs-native"

inherit autotools binconfig perlnative

SHRT_VER = "${@oe.utils.trim_version("${PV}", 2)}"
SRC_URI = "http://web.mit.edu/kerberos/dist/${PN}/${SHRT_VER}/${P}-signed.tar \
           file://0001-aclocal-Add-parameter-to-disable-keyutils-detection.patch \
"
SRC_URI[md5sum] = "56f0ae274b285320b8a597cb89442449"
SRC_URI[sha256sum] = "9abd94bb94a70996da0f8d90408957154bb543271b097e86c63eb33e5f5751b5"

S = "${WORKDIR}/${P}/src/"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[libedit] = "--with-libedit,--without-libedit,libedit"
PACKAGECONFIG[openssl] = "--with-pkinit-crypto-impl=openssl,,openssl"
PACKAGECONFIG[keyutils] = "--enable-keyutils,--disable-keyutils,keyutils"

EXTRA_OECONF += " --without-tcl --with-system-et --disable-rpath"
CACHED_CONFIGUREVARS += "krb5_cv_attr_constructor_destructor=yes ac_cv_func_regcomp=yes \
                  ac_cv_printf_positional=yes ac_cv_file__etc_environment=yes \
                  ac_cv_file__etc_TIMEZONE=no"

CFLAGS_append += "-DDESTRUCTOR_ATTR_WORKS=1 -I${STAGING_INCDIR}/et"
LDFLAGS_append += "-lpthread"

FILES_${PN}-doc += "${datadir}/examples"
FILES_${PN} += "${datadir}/gnats"
FILES_${PN}-dbg += "${libdir}/krb5/plugins/*/.debug"

krb5_do_unpack() {
    # ${P}-signed.tar contains ${P}.tar.gz.asc and ${P}.tar.gz
    tar xzf ${WORKDIR}/${P}.tar.gz -C ${WORKDIR}/
}

python do_unpack() {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('krb5_do_unpack', d)
}

do_configure() {
    gnu-configize --force
    autoreconf
    oe_runconf
}
