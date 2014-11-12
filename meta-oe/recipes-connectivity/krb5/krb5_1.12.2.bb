SUMMARY = "A network authentication protocol"
DESCRIPTION = "Kerberos is a system for authenticating users and services on a network. \
 Kerberos is a trusted third-party service.  That means that there is a \
 third party (the Kerberos server) that is trusted by all the entities on \
 the network (users and services, usually called "principals"). \
 . \
 This is the MIT reference implementation of Kerberos V5. \
 . \
 This package contains the Kerberos key server (KDC).  The KDC manages all \
 authentication credentials for a Kerberos realm, holds the master keys \
 for the realm, and responds to authentication requests.  This package \
 should be installed on both master and slave KDCs."

HOMEPAGE = "http://web.mit.edu/Kerberos/"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/../NOTICE;md5=450c80c6258ce03387bd09df37638ebc"
DEPENDS = "ncurses util-linux e2fsprogs e2fsprogs-native"

inherit autotools-brokensep binconfig perlnative

SHRT_VER = "${@oe.utils.trim_version("${PV}", 2)}"
SRC_URI = "http://web.mit.edu/kerberos/dist/${BPN}/${SHRT_VER}/${BP}-signed.tar \
           file://0001-aclocal-Add-parameter-to-disable-keyutils-detection.patch \
           file://0001-Return-only-new-keys-in-randkey-CVE-2014-5351.patch \
           file://debian-suppress-usr-lib-in-krb5-config.patch;striplevel=2 \
           file://crosscompile_nm.patch \
           file://etc/init.d/krb5-kdc \
           file://etc/init.d/krb5-admin-server \
           file://etc/default/krb5-kdc \
           file://etc/default/krb5-admin-server \
"
SRC_URI[md5sum] = "357f1312b7720a0a591e22db0f7829fe"
SRC_URI[sha256sum] = "09bd180107b5c2b3b7378c57c023fb02a103d4cac39d6f2dd600275d7a4f3744"

S = "${WORKDIR}/${BP}/src/"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[libedit] = "--with-libedit,--without-libedit,libedit"
PACKAGECONFIG[openssl] = "--with-pkinit-crypto-impl=openssl,,openssl"
PACKAGECONFIG[keyutils] = "--enable-keyutils,--disable-keyutils,keyutils"
PACKAGECONFIG[ldap] = "--with-ldap,--without-ldap,openldap"
PACKAGECONFIG[readline] = "--with-readline,--without-readline,readline"

EXTRA_OECONF += " --without-tcl --with-system-et --disable-rpath"
CACHED_CONFIGUREVARS += "krb5_cv_attr_constructor_destructor=yes ac_cv_func_regcomp=yes \
                  ac_cv_printf_positional=yes ac_cv_file__etc_environment=yes \
                  ac_cv_file__etc_TIMEZONE=no"

CFLAGS_append += "-DDESTRUCTOR_ATTR_WORKS=1 -I${STAGING_INCDIR}/et"
LDFLAGS_append += "-lpthread"

FILES_${PN} += "${datadir}/gnats"
FILES_${PN}-doc += "${datadir}/examples"
FILES_${PN}-dbg += "${libdir}/krb5/plugins/*/.debug"

krb5_do_unpack() {
    # ${P}-signed.tar contains ${P}.tar.gz.asc and ${P}.tar.gz
    tar xzf ${WORKDIR}/${BP}.tar.gz -C ${WORKDIR}/
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

do_install_append() {
    mkdir -p ${D}/etc/init.d ${D}/etc/default
    install -m 0755 ${WORKDIR}/etc/init.d/* ${D}/etc/init.d
    install -m 0644 ${WORKDIR}/etc/default/* ${D}/etc/default
}
