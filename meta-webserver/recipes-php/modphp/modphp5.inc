SECTION = "console/network"
DESCRIPTION = "A server-side, HTML-embedded scripting language. This package provides the apache php module."
LICENSE = "PHP-3.0"
DEPENDS = "apache2-native apache2 zlib bzip2"
INC_PR = "r1"

SRC_URI = "http://www.php.net/distributions/php-${PV}.tar.bz2 \
           file://configure.patch \
           file://pthread-check-threads-m4.patch \
           file://70_mod_php5.conf \
"

S = "${WORKDIR}/php-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=cb564efdf78cce8ea6e4b5a4f7c05d97"

inherit autotools

CFLAGS += " -g -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED -I${STAGING_INCDIR}/apache2"

EXTRA_OECONF = "--with-apxs2=${STAGING_BINDIR_CROSS}/apxs \
                --enable-maintainer-zts \
                --without-mysql \
                --enable-force-cgi-redirect \
                --disable-cgi \
                --disable-cli \
                --disable-pdo \
                --without-pear \
                --without-iconv \
                --disable-ipv6 \
                --disable-xml \
                --disable-xmlreader \
                --disable-xmlwriter \
                --disable-simplexml \
                --disable-libxml \
                --disable-dom \
                --disable-rpath \
                --libdir=${libdir}/php5 \
                --with-zlib --with-zlib-dir=${STAGING_DIR_TARGET}${exec_prefix} \
                --with-bz2=${STAGING_DIR_TARGET}${exec_prefix} \
                --enable-zip \
                --enable-mbstring \
                --with-config-file-path=${sysconfdir}/php/apache2-php5"

PACKAGECONFIG ??= "mysql"
PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config,--without-mysqli,mysql5"
PACKAGECONFIG[pgsql] = "--with-pgsql=${STAGING_DIR_TARGET}${exec_prefix},--without-pgsql,mysql5"

acpaths = ""

do_configure_prepend () {
    rm -f build/libtool.m4 ltmain.sh aclocal.m4
    find . -name config.m4 | xargs -n1 sed -i 's!APXS_HTTPD=.*!APXS_HTTPD=${STAGING_BINDIR_NATIVE}/httpd!'
}

do_configure_append() {
    # No libtool, we really don't want rpath set...
    sed -i 's|^hardcode_libdir_flag_spec=.*|hardcode_libdir_flag_spec=""|g' ${HOST_SYS}-libtool
    sed -i 's|^runpath_var=LD_RUN_PATH|runpath_var=DIE_RPATH_DIE|g' ${HOST_SYS}-libtool
}

do_install  () {
    install -d ${D}${libdir}/apache2/modules
    install -d ${D}${sysconfdir}/apache2/modules.d
    install -d ${D}${sysconfdir}/php/apache2-php5
    install -m 755  libs/libphp5.so ${D}${libdir}/apache2/modules
    sed -i s,lib/,${libdir}/, ${WORKDIR}/70_mod_php5.conf
    install -m 644  ${WORKDIR}/70_mod_php5.conf ${D}${sysconfdir}/apache2/modules.d
    cat ${S}/php.ini-production | \
        sed -e 's,extension_dir = \"\./\",extension_dir = \"/usr/lib/extensions\",' \
        > ${D}${sysconfdir}/php/apache2-php5/php.ini

    install -d ${D}${bindir}
    install -m 755 scripts/phpize ${D}${bindir}
    install -m 755 scripts/php-config ${D}${bindir}
    cat aclocal-copy/libtool.m4 aclocal-copy/lt~obsolete.m4 aclocal-copy/ltoptions.m4 \
        aclocal-copy/ltsugar.m4 aclocal-copy/ltversion.m4 > build/libtool.m4

    oe_runmake install-build install-headers INSTALL_ROOT=${D}
}

SYSROOT_PREPROCESS_FUNCS += "php_sysroot_preprocess"

php_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    install -m 755 ${D}${bindir}/phpize ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    install -m 755 ${D}${bindir}/php-config ${SYSROOT_DESTDIR}${bindir_crossscripts}/

    sed -i 's!eval echo /!eval echo ${STAGING_DIR_HOST}/!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/phpize
    sed -i 's!^include_dir=.*!include_dir=${STAGING_INCDIR}/php!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/php-config
}

RDEPENDS_${PN} = "apache2"

FILES_${PN} = "${libdir}/apache2 ${sysconfdir}"
FILES_${PN}-dev += "${bindir}/phpize ${bindir}/php-config ${libdir}/php5"
FILES_${PN}-dbg += "${libdir}/apache2/modules/.debug"

