DESCRIPTION = "A server-side, HTML-embedded scripting language. This package provides the CGI."
HOMEPAGE = "http://www.php.net"
SECTION = "console/network"
LICENSE = "PHP-3.0"
BBCLASSEXTEND = "native"
DEPENDS = "zlib libxml2 virtual/libiconv php-native lemon-native mysql5 \
           libc-client openssl sqlite3"
DEPENDS_virtclass-native = "zlib-native libxml2-native"

INC_PR = "r4"

SRC_URI = "http://www.php.net/distributions/php-${PV}.tar.bz2"

S = "${WORKDIR}/php-${PV}"

inherit autotools pkgconfig pythonnative gettext

SSTATE_SCAN_FILES += "build-defs.h"

# Common EXTRA_OECONF
COMMON_EXTRA_OECONF = "--enable-sockets --enable-pcntl --enable-shared"
EXTRA_OECONF = "--enable-mbstring --enable-discard-path --enable-wddx \
                --enable-magic-quotes --enable-fastcgi --enable-fpm \
                --with-imap=${STAGING_DIR_HOST} \
                --with-gettext=${STAGING_LIBDIR}/.. \
                --with-imap-ssl=${STAGING_DIR_HOST} \
                --with-zlib --with-zlib-dir=${STAGING_LIBDIR}/.. \
                --with-iconv=${STAGING_LIBDIR}/.. \
                --with-libxml-dir=${STAGING_BINDIR_CROSS} \
                --disable-embedded-mysqli \
                --with-mysql="${STAGING_DIR_TARGET}${prefix}" \
                --with-mysqli="${STAGING_BINDIR_CROSS}/mysql_config" \
        --with-sqlite3=${STAGING_LIBDIR}/.. --enable-sqlite-utf8 \
                --with-pdo-mysql="${STAGING_BINDIR_CROSS}/mysql_config" \
                --with-pdo-sqlite=${STAGING_LIBDIR}/.. ${COMMON_EXTRA_OECONF} \
"
EXTRA_OECONF_virtclass-native = "--with-cgi --without-libpng --without-libjpeg \
                --with-zlib --with-zlib-dir=${STAGING_LIBDIR_NATIVE}/.. \
                --with-libxml-dir=${STAGING_BINDIR_NATIVE} \
                ${COMMON_EXTRA_OECONF} \
"

LIBS_virtclass-native = " -lxml2 "
LIBS_pn-php =" -lpthread "
export LIBS
THREADS_pn-php = "pthread"
export THREADS
export PHP_NATIVE_DIR = "${STAGING_BINDIR_NATIVE}"
export PHP_PEAR_PHP_BIN = "${STAGING_BINDIR_NATIVE}/php"
CFLAGS += " -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED -D_GNU_SOURCE"

acpaths = ""

do_install  () {
    oe_runmake 'INSTALL_ROOT=${D}' install
}

do_install_append_pn-php-native() {
    rm -rf ${D}/${libdir}/php/.registry
    rm -rf ${D}/${libdir}/php/.channels
    rm -rf ${D}/${libdir}/php/.[a-z]*
}

# fixme
do_install_append_pn-php() {
    install -d ${D}/${sysconfdir}/
    if [ -d ${D}/${STAGING_DIR_NATIVE}/${sysconfdir} ];then
         mv ${D}/${STAGING_DIR_NATIVE}/${sysconfdir}/* ${D}/${sysconfdir}/
    fi
    rm -rf ${D}/${TMPDIR}
    rm -rf ${D}/.registry
    rm -rf ${D}/.channels
    rm -rf ${D}/.[a-z]*
    rm -rf ${D}/var
    rm -f  ${D}/${sysconfdir}/php-fpm.conf.default
    sed -i 's:${STAGING_DIR_NATIVE}::g' ${D}/${sysconfdir}/pear.conf
    install -m 0644 ${WORKDIR}/php-fpm.conf ${D}/${sysconfdir}/php-fpm.conf
    install -d ${D}/${sysconfdir}/apache2/conf.d
    install -m 0644 ${WORKDIR}/php-fpm-apache.conf ${D}/${sysconfdir}/apache2/conf.d/php-fpm.conf
    install -d ${D}${sysconfdir}/init.d
    sed -i 's:=/usr/sbin:=${sbindir}:g' ${S}/sapi/fpm/init.d.php-fpm
    sed -i 's:=/etc:=${sysconfdir}:g' ${S}/sapi/fpm/init.d.php-fpm
    sed -i 's:=/var:=${localstatedir}:g' ${S}/sapi/fpm/init.d.php-fpm
    install -m 0755 ${S}/sapi/fpm/init.d.php-fpm ${D}${sysconfdir}/init.d/php-fpm
    TMP=`dirname ${D}/${TMPDIR}`
    while test ${TMP} != ${D}; do
        rmdir ${TMP}
        TMP=`dirname ${TMP}`;
    done
}

PACKAGES = "${PN}-dbg ${PN}-cli ${PN}-cgi ${PN}-fpm ${PN}-fpm-apache2 ${PN}-pear ${PN}-dev ${PN}-staticdev ${PN}-doc ${PN}"

RDEPENDS_${PN}-pear = "${PN}"
RDEPENDS_${PN}-cli = "${PN}"
RDEPENDS_${PN}-dev = "${PN}"

INITSCRIPT_PACKAGES = "${PN}-fpm"
inherit update-rc.d

FILES_${PN}-dbg =+ "${bindir}/.debug"
FILES_${PN}-doc += "${libdir}/php/doc"
FILES_${PN}-cli = "${bindir}/php"
FILES_${PN}-cgi = "${bindir}/php-cgi"
FILES_${PN}-fpm = "${sbindir}/php-fpm ${sysconfdir}/php-fpm.conf ${datadir}/fpm ${sysconfdir}/init.d/php-fpm"
FILES_${PN}-fpm-apache2 = "${sysconfdir}/apache2/conf.d/php-fpm.conf"
CONFFILES_${PN}-fpm = "${sysconfdir}/php-fpm.conf"
CONFFILES_${PN}-fpm-apache2 = "${sysconfdir}/apache2/conf.d/php-fpm.conf"
INITSCRIPT_NAME_${PN}-fpm = "php-fpm"
INITSCRIPT_PARAMS_${PN}-fpm = "defaults 60"
FILES_${PN}-pear = "${bindir}/pear* ${bindir}/pecl ${libdir}/php/PEAR \
                ${libdir}/php/PEAR.php ${libdir}/php/System.php \
                ${libdir}php/peclcmd.php ${libdir}/php/pearcmd.php \
                ${libdir}/php/.channels  ${libdir}/php/.channels/.alias  \
                ${libdir}/php/.channels\__uri.reg \
                ${libdir}/php/.channels\pear.php.net.reg \
                ${libdir}/php/.channels/pecl.php.net.reg \
                ${libdir}/php/.registry ${libdir}/php/Archive/Tar.php \
                ${libdir}/php/Console/Getopt.php ${libdir}/php/OS/Guess.php \
                ${sysconfdir}/pear.conf"
FILES_${PN}-dev = "${includedir}/php ${libdir}/build ${bindir}/phpize \
                ${bindir}/php-config ${libdir}/php/.depdb \
                ${libdir}/php/.depdblock ${libdir}/php/.filemap \
                ${libdir}/php/.lock ${libdir}/php/test"
FILES_${PN} = "${libdir}/php"
FILES_${PN} += "${bindir}"
