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

LDAP_VER = "${@'.'.join(bb.data.getVar('PV',d,1).split('.')[0:2])}"

SRC_URI = "ftp://ftp.openldap.org/pub/OpenLDAP/openldap-release/${P}.tgz"
SRC_URI += "file://openldap-m4-pthread.patch"
SRC_URI += "file://initscript"
SRC_URI[md5sum] = "90150b8c0d0192e10b30157e68844ddf"
SRC_URI[sha256sum] = "5a5ede91d5e8ab3c7f637620aa29a3b96eb34318a8b26c8eef2d2c789fc055e3"

# The original top.mk used INSTALL, not INSTALL_STRIP_PROGRAM when
# installing .so and executables, this fails in cross compilation
# environments
SRC_URI += "file://install-strip.patch"

inherit autotools

# OPTIONS
# The following two variables can be set in a distro or local.conf
# to switch features on.  Each feature foo defines OPENLDAP_OPTION_foo
# and OPENLDAP_DEPENDS_foo in this file - to include feature foo add
# the two variables into the setting of the options below (please use
# += because that means this can be done in *both* distro.conf and
# local.conf!
OPENLDAP_OPTIONS ?= ""
OPENLDAP_DEPENDS ?= ""

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
#
# Disable TLS to remove the need for openssl/libcrypto
OPENLDAP_OPTION_tls  ?= "--with-tls"
# set the following to "openssl" to build tls support
OPENLDAP_DEPENDS_tls ?= "gnutls"
EXTRA_OECONF += "${OPENLDAP_OPTION_tls}"
DEPENDS += "${OPENLDAP_DEPENDS_tls}"
#
# Disable Cyrus SASL, which may or may not be working at present...
OPENLDAP_OPTION_sasl  ?= "--without-cyrus-sasl"
# set the following to "cyrus-sasl" to build SASL support
OPENLDAP_DEPENDS_sasl ?= ""
EXTRA_OECONF += "${OPENLDAP_OPTION_sasl}"
DEPENDS += "${OPENLDAP_DEPENDS_sasl}"

# SLAPD options
#
# UNIX crypt(3) passwd support:
EXTRA_OECONF += "--enable-crypt"
#
# Enable dynamic module loading.  If this is *disabled* the
# dependency on libtool is removed (to disable set the following
# to variables to "" in a .conf file).
OPENLDAP_OPTION_modules += "lt_cv_dlopen_self=yes --enable-modules"
OPENLDAP_DEPENDS_modules += "libtool"
EXTRA_OECONF += " ${OPENLDAP_OPTION_modules}"
DEPENDS += "${OPENLDAP_DEPENDS_modules}"

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
# To disable this set all three of the following variables to <empty> in
# a .conf file (this will allow ldbm to be build with gdbm).
#OPENLDAP_OPTION_bdb   ?= "--enable-bdb=mod"
OPENLDAP_DEPENDS_bdb  ?= "db"
EXTRA_OECONF += "${OPENLDAP_OPTION_bdb}"
DEPENDS += "${OPENLDAP_DEPENDS_bdb}"
#
#--enable-dnssrv       enable dnssrv backend no|yes|mod no
# This has no dependencies.
EXTRA_OECONF += "--enable-dnssrv=mod"
#
#--enable-hdb          enable Hierarchical DB backend no|yes|mod no
# This forces ldbm to use Berkeley too, remove to use gdbm
#OPENLDAP_OPTION_hdb   ?= "--enable-hdb=mod"
OPENLDAP_DEPENDS_hdb  ?= "db"
OPENLDAP_PACKAGE_hdb  ?= "${PN}-backend-hdb"
EXTRA_OECONF += "${OPENLDAP_OPTION_hdb}"
DEPENDS += "${OPENLDAP_DEPENDS_hdb}"
#
#--enable-ldap         enable ldap backend no|yes|mod no
# This has no dependencies
EXTRA_OECONF += "--enable-ldap=mod"
#
#--enable-ldbm         enable ldbm backend no|yes|mod no
# ldbm requires further specification of the underlying database API, because
# bdb is enabled above this must be set to berkeley, however the config
# defaults this correctly so --with-ldbm-api is *not* set.  The build will
# fail if bdb is removed (above) but not database is built to provide the
# support for ldbm (because the 'DEPENDS_ldbm' is empty below.)
#
# So to use gdbm set:
OPENLDAP_OPTION_ldbm = "--enable-ldbm=mod --with-ldbm-api=gdbm"
OPENLDAP_DEPENDS_ldbm = "gdbm"
# And clear the bdb and hdb settings.
OPENLDAP_OPTION_ldbm ?= "--enable-ldbm=mod"
OPENLDAP_DEPENDS_ldbm ?= ""
EXTRA_OECONF += "${OPENLDAP_OPTION_ldbm}"
DEPENDS += "${OPENLDAP_DEPENDS_ldbm}"
#
#--enable-meta         enable metadirectory backend no|yes|mod no
# No dependencies
EXTRA_OECONF += "--enable-meta=mod"
#
#--enable-monitor      enable monitor backend no|yes|mod yes
EXTRA_OECONF += "--enable-monitor=mod"
#
#--enable-null         enable null backend no|yes|mod no
EXTRA_OECONF += "--enable-null=mod"
#
#--enable-passwd       enable passwd backend no|yes|mod no
EXTRA_OECONF += " --enable-passwd=mod"
#
#--enable-perl         enable perl backend no|yes|mod no
#  This requires a loadable perl dynamic library, if enabled without
#  doing something appropriate (building perl?) the build will pick
#  up the build machine perl - not good.
OPENLDAP_OPTION_perl ?= "--enable-perl=mod"
OPENLDAP_DEPENDS_perl ?= "perl"
#EXTRA_OECONF += "${OPENLDAP_OPTION_perl}"
#DEPENDS += "${OPENLDAP_DEPENDS_perl}"
#
#--enable-shell        enable shell backend no|yes|mod no
EXTRA_OECONF += "--enable-shell=mod"
#
#--enable-sql          enable sql backend no|yes|mod no
# sql requires some sql backend which provides sql.h, sqlite* provides
# sqlite.h (which may be compatible but hasn't been tried.)
OPENLDAP_OPTION_sql ?= "--enable-sql=mod"
OPENLDAP_DEPENDS_sql ?= "sql"
#EXTRA_OECONF += "${OPENLDAP_OPTION_sql}"
#DEPENDS += "${OPENLDAP_DEPENDS_sql}"
#
#--enable-dyngroup     Dynamic Group overlay no|yes|mod no
#  This is a demo, Proxy Cache defines init_module which conflicts with the
#  same symbol in dyngroup
#EXTRA_OECONF += "--enable-dyngroup=mod"
#
#--enable-proxycache   Proxy Cache overlay no|yes|mod no
EXTRA_OECONF += "--enable-proxycache=mod"
FILES_${PN}-overlay-proxycache = "${md}/pcache-*.so.*"
PACKAGES += "${PN}-overlay-proxycache"
#
# LOCAL OPTION OVERRIDES
# The distro/lcoal options must be added in *last*
EXTRA_OECONF += "${OPENLDAP_OPTIONS}"
DEPENDS      += "${OPENLDAP_DEPENDS}"

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
FILES_${PN}-slapd = "${sysconfdir}/init.d ${libexecdir}/slapd ${sbindir} ${localstatedir}/run \
    ${sysconfdir}/openldap/slapd.* ${sysconfdir}/openldap/schema \
    ${sysconfdir}/openldap/DB_CONFIG.example"
FILES_${PN}-slurpd = "${libexecdir}/slurpd ${localstatedir}/openldap-slurp ${localstatedir}/run"
FILES_${PN}-bin = "${bindir}"
FILES_${PN}-dev = "${includedir} ${libdir}/lib*.so ${libdir}/*.la ${libdir}/*.a ${libexecdir}/openldap/*.a ${libexecdir}/openldap/*.la ${libexecdir}/openldap/*.so"
FILES_${PN}-dbg += "${libexecdir}/openldap/.debug"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    cat ${WORKDIR}/initscript > ${D}${sysconfdir}/init.d/openldap
    chmod 755 ${D}${sysconfdir}/init.d/openldap
    # This is duplicated in /etc/openldap and is for slapd
    rm -f ${D}${localstatedir}/openldap-data/DB_CONFIG.example
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
    backend_dir    = bb.data.expand('${libexecdir}/openldap', d)
    do_split_packages(d, backend_dir, 'back_([a-z]*)\-.*\.so\..*$', 'openldap-backend-%s', 'OpenLDAP %s backend', extra_depends='', allow_links=True)

    metapkg = "openldap-backends"
    bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
    bb.data.setVar('FILES_' + metapkg, "", d)
    metapkg_rdepends = []
    packages = bb.data.getVar('PACKAGES', d, 1).split()
    for pkg in packages[1:]:
        if pkg.count("openldap-backend-") and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale"):
            metapkg_rdepends.append(pkg)
    bb.data.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
    bb.data.setVar('DESCRIPTION_' + metapkg, 'OpenLDAP backends meta package', d)
    packages.append(metapkg)
    bb.data.setVar('PACKAGES', ' '.join(packages), d)
}
