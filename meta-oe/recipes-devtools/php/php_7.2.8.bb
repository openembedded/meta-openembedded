require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=67e369bc8d1f2e641236b8002039a6a2"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://0001-acinclude.m4-skip-binconfig-check-for-libxml.patch \
            file://0001-fix-error-caused-by-a-new-variable-is-declared-after.patch \
            file://CVE-2017-9120.patch \
           "
SRC_URI_append_class-target = " \
                                file://pear-makefile.patch \
                                file://phar-makefile.patch \
                                file://0001-opcache-config.m4-enable-opcache.patch \
                                "

SRC_URI[md5sum] = "4c5e420b85d12306d84c7e1fa9b085c5"
SRC_URI[sha256sum] = "1f8068f520a60fff3db19be1b849f0c02a33a0fd8b34b7ae05556ef682187ee6"

PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config \
                        --with-pdo-mysql=${STAGING_BINDIR_CROSS}/mysql_config \
                        ,--without-mysqli --without-pdo-mysql \
                        ,mysql5"

FILES_${PN}-fpm += "${sysconfdir}/php-fpm.d/www.conf.default"
