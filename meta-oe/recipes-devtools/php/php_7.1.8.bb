require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=c0af599f66d0461c5837c695fcbc5c1e"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://0001-Specify-tag-with-libtool.patch \
           "
SRC_URI[md5sum] = "d22451dc20bbdf4b8bb656dc787c2a36"
SRC_URI[sha256sum] = "7064a00a9450565190890c7a4be04e646e0be73b2e0f8c46ae34419f343ca2f8"

PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config \
                        --with-pdo-mysql=${STAGING_BINDIR_CROSS}/mysql_config \
                        ,--without-mysqli --without-pdo-mysql \
                        ,mysql5"

FILES_${PN}-fpm += "${sysconfdir}/php-fpm.d/www.conf.default"
