# OpenLDAP, a license free (see http://www.OpenLDAP.org/license.html)
#
DESCRIPTION = "OpenLDAP Software is an open source implementation of the Lightweight Directory Access Protocol."
HOMEPAGE = "http://www.OpenLDAP.org/license.html"
# The OpenLDAP Public License - see the HOMEPAGE - defines
# the license.  www.openldap.org claims this is Open Source
# (see http://www.openldap.org), the license appears to be
# basically BSD.  opensource.org does not record this license
# at present (so it is apparently not OSI certified).
LICENSE = "OpenLDAP"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=3d82d3085f228af211a6502c7ea7c3c7"
SECTION = "libs"

LDAP_VER = "${@'.'.join(d.getVar('PV',1).split('.')[0:2])}"

SRC_URI = "ftp://ftp.openldap.org/pub/OpenLDAP/openldap-release/${P}.tgz \
    file://openldap-m4-pthread.patch \
    file://kill-icu.patch \
    file://initscript \
"
SRC_URI[md5sum] = "90150b8c0d0192e10b30157e68844ddf"
SRC_URI[sha256sum] = "5a5ede91d5e8ab3c7f637620aa29a3b96eb34318a8b26c8eef2d2c789fc055e3"

DEPENDS = "util-linux groff-native"

PR = "r1"
# The original top.mk used INSTALL, not INSTALL_STRIP_PROGRAM when
# installing .so and executables, this fails in cross compilation
# environments
SRC_URI += "file://install-strip.patch"

inherit autotools

# CV SETTINGS
# Required to work round AC_FUNC_MEMCMP which gets the wrong answer
# when cross compiling (should be in site?)
EXTRA_OECONF += "ac_cv_func_memcmp_working=yes"

# CONFIG DEFINITIONS
# The following is necessary because it cannot be determined for a
# cross compile automagically.  Select should yield fine on all OE
# systems...
EXTRA_OECONF += "--with-yielding-select=yes"
# Shared libraries are nice...
EXTRA_OECONF += "--enable-dynamic"

PACKAGECONFIG ??= "gnutls modules \
                   ldap meta monitor null passwd shell proxycache dnssrv \
"
#--with-tls              with TLS/SSL support auto|openssl|gnutls [auto]
PACKAGECONFIG[gnutls] = "--with-tls=gnutls,,gnutls"
PACKAGECONFIG[openssl] = "--with-tls=openssl,,openssl"

PACKAGECONFIG[sasl] = "--with-cyrus-sasl,--without-cyrus-sasl,cyrus-sasl"
PACKAGECONFIG[modules] = "lt_cv_dlopen_self=yes --enable-modules,--disable-modules,libtool"

# SLAPD options
#
# UNIX crypt(3) passwd support:
EXTRA_OECONF += "--enable-crypt"

# SLAPD BACKEND
#
# The backend must be set by the configuration.  This controls the
# required database, the default database, bdb, is turned off but
# can be turned back on again and it *is* below!  The monitor backend
# is also disabled.  If you try to change the backends but fail to
# enable a single one the build will fail in an obvious way.
#
EXTRA_OECONF += "--disable-bdb --disable-hdb --disable-monitor"
#
# Backends="bdb dnssrv hdb ldap ldbm meta monitor null passwd perl shell sql"
#
# Note that multiple backends can be built.  The ldbm backend requires a
# build-time choice of database API.  The bdb backend forces this to be
# DB4.  To use the gdbm (or other) API the Berkely database module must
# be removed from the build.
md = "${libexecdir}/openldap"
#
#--enable-bdb          enable Berkeley DB backend no|yes|mod yes
# The Berkely DB is the standard choice.  This version of OpenLDAP requires
# the version 4 implementation or better.
PACKAGECONFIG[bdb] = "--enable-bdb=mod,--enable-bdb=no,db"

#--enable-dnssrv       enable dnssrv backend no|yes|mod no
PACKAGECONFIG[dnssrv] = "--enable-dnssrv=mod,--enable-dnssrv=no"

#--enable-hdb          enable Hierarchical DB backend no|yes|mod no
# This forces ldbm to use Berkeley too, remove to use gdbm
PACKAGECONFIG[hdb] = "--enable-hdb=mod,--enable-hdb=no,db"

#--enable-ldap         enable ldap backend no|yes|mod no
PACKAGECONFIG[ldap] = "--enable-ldap=mod,--enable-ldap=no,"

#--enable-ldbm         enable ldbm backend no|yes|mod no
# ldbm requires further specification of the underlying database API, because
# bdb is enabled above this must be set to berkeley, however the config
# defaults this correctly so --with-ldbm-api is *not* set.  The build will
# fail if bdb is removed, but no database is built to provide the
# support for ldbm
# guide.html:<P>back-ldbm was both slow and unreliable. Its byzantine indexing code was prone to spontaneous corruption, as were the underlying database libraries that were commonly used (e.g. GDBM or NDBM). back-bdb and back-hdb are superior in every aspect, with simplified indexing to avoid index corruption, fine-grained locking for greater concurrency, hierarchical caching for greater performance, streamlined on-disk format for greater efficiency and portability, and full transaction support for greater reliability.</P>
# configure: WARNING: unrecognized options: --disable-silent-rules, --enable-ldbm, --with-ldbm-api
#PACKAGECONFIG[ldbm] = "--enable-ldbm=mod --with-ldbm-api=gdbm,--enable-ldbm-no,gdbm"

#--enable-meta         enable metadirectory backend no|yes|mod no
PACKAGECONFIG[meta] = "--enable-meta=mod,--enable-meta=no,"

#--enable-monitor      enable monitor backend no|yes|mod yes
PACKAGECONFIG[monitor] = "--enable-monitor=mod,--enable-monitor=no,"

#--enable-null         enable null backend no|yes|mod no
PACKAGECONFIG[null] = "--enable-null=mod,--enable-null=no,"

#--enable-passwd       enable passwd backend no|yes|mod no
PACKAGECONFIG[passwd] = "--enable-passwd=mod,--enable-passwd=no,"

#--enable-perl         enable perl backend no|yes|mod no
#  This requires a loadable perl dynamic library, if enabled without
#  doing something appropriate (building perl?) the build will pick
#  up the build machine perl - not good (inherit perlnative?)
PACKAGECONFIG[perl] = "--enable-perl=mod,--enable-perl=no,perl"

#--enable-shell        enable shell backend no|yes|mod no
# configure: WARNING: Use of --without-threads is recommended with back-shell
PACKAGECONFIG[shell] = "--enable-shell=mod --without-threads,--enable-shell=no,"

#--enable-sql          enable sql backend no|yes|mod no
# sql requires some sql backend which provides sql.h, sqlite* provides
# sqlite.h (which may be compatible but hasn't been tried.)
PACKAGECONFIG[sql] = "--enable-sql=mod,--enable-sql=no,sqlite3"

#--enable-dyngroup     Dynamic Group overlay no|yes|mod no
#  This is a demo, Proxy Cache defines init_module which conflicts with the
#  same symbol in dyngroup
PACKAGECONFIG[dyngroup] = "--enable-dyngroup=mod,--enable-dyngroup=no,"

#--enable-proxycache   Proxy Cache overlay no|yes|mod no
PACKAGECONFIG[proxycache] = "--enable-proxycache=mod,--enable-proxycache=no,"
FILES_${PN}-overlay-proxycache = "${md}/pcache-*.so.*"
PACKAGES += "${PN}-overlay-proxycache"

CPPFLAGS_append = " -D_GNU_SOURCE"

do_configure() {
    cp ${STAGING_DATADIR_NATIVE}/libtool/config/ltmain.sh ${S}/build
    rm -f ${S}/libtool
    aclocal
    libtoolize --force --copy
    gnu-configize
    autoconf
    oe_runconf
}

#FIXME: this is a hack, at present an openldap build will pick up the header
# files from staging rather than the local ones (bad -I order), so remove
# the headers (from openldap-old.x) before compiling...
do_compile_prepend() {
    (
        cd ${STAGING_INCDIR}
        rm -f ldap.h ldap_*.h
    )
    (
        cd ${STAGING_LIBDIR}
        rm -f libldap* liblber*
    )
}

LEAD_SONAME = "libldap-${LDAP_VER}.so.*"

# The executables go in a separate package.  This allows the
# installation of the libraries with no daemon support.
# Each module also has its own package - see above.
PACKAGES += "${PN}-slapd ${PN}-slurpd ${PN}-bin"

# Package contents - shift most standard contents to -bin
FILES_${PN} = "${libdir}/lib*.so.* ${sysconfdir}/openldap/ldap.* ${localstatedir}/openldap-data"
FILES_${PN}-slapd = "${sysconfdir}/init.d ${libexecdir}/slapd ${sbindir} ${localstatedir}/run ${localstatedir}/volatile/run \
    ${sysconfdir}/openldap/slapd.* ${sysconfdir}/openldap/schema \
    ${sysconfdir}/openldap/DB_CONFIG.example"
FILES_${PN}-slurpd = "${libexecdir}/slurpd ${localstatedir}/openldap-slurp ${localstatedir}/run ${localstatedir}/volatile/run"
FILES_${PN}-bin = "${bindir}"
FILES_${PN}-dev = "${includedir} ${libdir}/lib*.so ${libdir}/*.la ${libdir}/*.a ${libexecdir}/openldap/*.a ${libexecdir}/openldap/*.la ${libexecdir}/openldap/*.so"
FILES_${PN}-dbg += "${libexecdir}/openldap/.debug"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    cat ${WORKDIR}/initscript > ${D}${sysconfdir}/init.d/openldap
    chmod 755 ${D}${sysconfdir}/init.d/openldap
    # This is duplicated in /etc/openldap and is for slapd
    rm -f ${D}${localstatedir}/openldap-data/DB_CONFIG.example
    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

pkg_postinst_${PN}-slapd () {
    if test -n "${D}"; then
        D="-r $D"
    fi
    update-rc.d $D openldap defaults
}

pkg_prerm_${PN}-slapd () {
    if test -n "${D}"; then
        D="-r $D"
    fi
    update-rc.d $D openldap remove
}

PACKAGES_DYNAMIC += "^openldap-backends.* ^openldap-backend-.*"

python populate_packages_prepend () {
    backend_dir    = d.expand('${libexecdir}/openldap')
    do_split_packages(d, backend_dir, 'back_([a-z]*)\-.*\.so\..*$', 'openldap-backend-%s', 'OpenLDAP %s backend', extra_depends='', allow_links=True)

    metapkg = "openldap-backends"
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")
    metapkg_rdepends = []
    packages = d.getVar('PACKAGES', 1).split()
    for pkg in packages[1:]:
        if pkg.count("openldap-backend-") and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale"):
            metapkg_rdepends.append(pkg)
    d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
    d.setVar('DESCRIPTION_' + metapkg, 'OpenLDAP backends meta package')
    packages.append(metapkg)
    d.setVar('PACKAGES', ' '.join(packages))
}
