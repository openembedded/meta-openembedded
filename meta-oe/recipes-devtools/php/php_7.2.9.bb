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

SRC_URI[md5sum] = "175023bc43fd8f883e60849a02a03b93"
SRC_URI[sha256sum] = "e9e3aaa6c317b7fea78246a758b017544366049d2789ad5a44fe9398464c53a8"

PACKAGECONFIG[mysql] = "--with-mysqli=${STAGING_BINDIR_CROSS}/mysql_config \
                        --with-pdo-mysql=${STAGING_BINDIR_CROSS}/mysql_config \
                        ,--without-mysqli --without-pdo-mysql \
                        ,mysql5"

FILES_${PN}-fpm += "${sysconfdir}/php-fpm.d/www.conf.default"
