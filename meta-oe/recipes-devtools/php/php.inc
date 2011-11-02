DESCRIPTION = "A server-side, HTML-embedded scripting language. This package provides the CGI."
HOMEPAGE = "http://www.php.net"
SECTION = "console/network"
LICENSE = "PHP"
BBCLASSEXTEND = "native"
DEPENDS = "zlib libxml2 virtual/libiconv php-native lemon-native mysql5 \
           libc-client openssl"
DEPENDS_virtclass-native = "zlib-native libxml2-native"

INC_PR = "r0"

SRC_URI = "http://www.php.net/distributions/php-${PV}.tar.bz2"

S = "${WORKDIR}/php-${PV}"

inherit autotools pkgconfig

# Common EXTRA_OECONF
COMMON_EXTRA_OECONF = "--enable-sockets --enable-pcntl --enable-shared"
EXTRA_OECONF = "--enable-mbstring --enable-discard-path --enable-wddx \
                --enable-magic-quotes --enable-fastcgi \
                --with-imap=${STAGING_DIR_HOST} \
                --with-imap-ssl=${STAGING_DIR_HOST} \
                --with-zlib --with-zlib-dir=${STAGING_LIBDIR}/.. \
                --with-iconv=${STAGING_LIBDIR}/.. \
                --with-libxml-dir=${STAGING_BINDIR_CROSS} \
                --disable-embedded-mysqli \
                --with-mysql="${STAGING_DIR_TARGET}${prefix}" \
                --with-mysqli="${STAGING_BINDIR_CROSS}/mysql_config" \
                --with-pdo-mysql="${STAGING_BINDIR_CROSS}/mysql_config" \
                --without-pdo-sqlite ${COMMON_EXTRA_OECONF} \
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
LD_LIBRARY_PATH_virtclass-native = "${STAGING_LIBDIR_NATIVE}"
LD_LIBRARY_PATH_pn-php = "${STAGING_LIBDIR}"
export LD_LIBRARY_PATH
export PHP_NATIVE_DIR = "${STAGING_BINDIR_NATIVE}"
export PHP_PEAR_PHP_BIN = "${STAGING_BINDIR_NATIVE}/php"
CFLAGS += " -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED -D_GNU_SOURCE"

acpaths = ""

do_install  () {
    oe_runmake 'INSTALL_ROOT=${D}' install
}

# fixme
do_install_append_pn-php() {
    mv ${D}/${STAGING_DIR_NATIVE}/${sysconfdir} ${D}/${sysconfdir}
    rm -rf ${D}/${STAGING_DIR_NATIVE}
    rm -rf ${D}/.registry
    rm -rf ${D}/.channels
    rm -rf ${D}/.[a-z]*
    sed -i 's:${STAGING_DIR_NATIVE}::g' ${D}/${sysconfdir}/pear.conf
}

PACKAGES = "${PN}-dbg ${PN}-cli ${PN}-cgi ${PN}-pear ${PN}-dev ${PN}-staticdev ${PN}-doc ${PN}"

RDEPENDS_${PN}-pear = "${PN}"
RDEPENDS_${PN}-cli = "${PN}"
RDEPENDS_${PN}-dev = "${PN}"


FILES_${PN}-dbg =+ "${bindir}/.debug"
FILES_${PN}-doc += "${libdir}/php/doc"
FILES_${PN}-cli = "${bindir}/php"
FILES_${PN}-cgi = "${bindir}/php-cgi"
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
