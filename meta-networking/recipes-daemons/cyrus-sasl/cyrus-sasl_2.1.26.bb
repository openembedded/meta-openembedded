SUMMARY = "Generic client/server library for SASL authentication"
SECTION = "console/network"
DEPENDS = "openssl virtual/db"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3f55e0974e3d6db00ca6f57f2d206396"

SRC_URI = "ftp://ftp.cyrusimap.org/cyrus-sasl/cyrus-sasl-${PV}.tar.gz \
	   file://avoid-to-call-AC_TRY_RUN.patch \
	   file://Fix-hardcoded-libdir.patch \
	   file://debian_patches_0009_sasldb_al.diff \
	   file://debian_patches_0014_avoid_pic_overwrite.diff \
	   file://sasl.h-include-stddef.h-for-size_t-on-NetBSD.patch \
	   "

inherit autotools-brokensep pkgconfig useradd

EXTRA_OECONF += "--with-dblib=berkeley \
                 --with-bdb-libdir=${STAGING_LIBDIR} \
                 --with-bdb-incdir=${STAGING_INCDIR} \
                 --with-bdb=db-5.3 \
                 --with-plugindir="${libdir}/sasl2/" \
                 andrew_cv_runpath_switch=none"

PACKAGECONFIG ??= "ntlm \
        ${@base_contains('DISTRO_FEATURES', 'ldap', 'ldap', '', d)} \
        ${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
        "
PACKAGECONFIG[gssapi] = "--enable-gssapi=yes,--enable-gssapi=no,krb5,"
PACKAGECONFIG[pam] = "--with-pam,--without-pam,libpam,"
PACKAGECONFIG[opie] = "--with-opie,--without-opie,opie,"
PACKAGECONFIG[des] = "--with-des,--without-des,,"
PACKAGECONFIG[ldap] = "--with-ldap=${STAGING_LIBDIR} --enable-ldapdb,--without-ldap --disable-ldapdb,openldap,"
PACKAGECONFIG[ntlm] = "--with-ntlm,--without-ntlm,,"

CFLAGS += "-fPIC"

do_configure_prepend () {
    rm -f acinclude.m4 config/libtool.m4

    # make it be able to work with db 5.0 version
    local sed_files="sasldb/db_berkeley.c utils/dbconverter-2.c"
    for sed_file in $sed_files; do
        sed -i 's#DB_VERSION_MAJOR == 4.*#(&) || DB_VERSION_MAJOR == 5#' $sed_file
    done
}

do_compile_prepend () {
    cd include
    ${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS} makemd5.c -o makemd5
    touch makemd5.o makemd5.lo makemd5
    cd ..
}

USERADD_PACKAGES = "${PN}-bin"
GROUPADD_PARAM_${PN}-bin = "--system mail"
USERADD_PARAM_${PN}-bin = "--system --home=/var/spool/mail -g mail cyrus"

SRC_URI[md5sum] = "a7f4e5e559a0e37b3ffc438c9456e425"
SRC_URI[sha256sum] = "8fbc5136512b59bb793657f36fadda6359cae3b08f01fd16b3d406f1345b7bc3"

PACKAGES =+ "${PN}-bin"

FILES_${PN}           += "${libdir}/sasl2/*.so*"
FILES_${PN}-bin       += "${bindir}"
FILES_${PN}-dev       += "${libdir}/sasl2/*.la"
FILES_${PN}-dbg       += "${libdir}/sasl2/.debug"
FILES_${PN}-staticdev += "${libdir}/sasl2/*.a"

INSANE_SKIP_${PN} += "dev-so"
