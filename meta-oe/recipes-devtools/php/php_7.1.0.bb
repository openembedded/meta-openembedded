require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=b602636d46a61c0ac0432bbf5c078fe4"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch"
SRC_URI[md5sum] = "54e364b60a88db77adb96aacb10f10a4"
SRC_URI[sha256sum] = "68bcfd7deed5b3474d81dec9f74d122058327e2bed0ac25bbc9ec70995228e61"

PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config \
                        --with-pdo-mysql=${STAGING_BINDIR_CROSS}/mysql_config \
                        ,--without-mysqli --without-pdo-mysql \
                        ,mysql5"

FILES_${PN}-fpm += "${sysconfdir}/php-fpm.d/www.conf.default"
