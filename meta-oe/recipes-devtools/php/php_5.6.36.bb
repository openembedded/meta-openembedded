require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=b602636d46a61c0ac0432bbf5c078fe4"

SRC_URI += "file://php5-change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://pthread-check-threads-m4.patch \
            file://0001-Add-lpthread-to-link.patch \
            file://acinclude-xml2-config.patch \
            file://0001-acinclude-use-pkgconfig-for-libxml2-config.patch \
            "

SRC_URI_append_class-target = " \
                                file://php5-pear-makefile.patch \
                                file://php5-phar-makefile.patch \
                                file://php5-0001-opcache-config.m4-enable-opcache.patch \
                                "

SRC_URI[md5sum] = "6ca12a0e52dcd99cf4d5b2d74c6cde6f"
SRC_URI[sha256sum] = "626a0e3f5d8a0e686a2b930f0dd3a0601fe3dcb5e43dd0e8c3fab631e64e172a"

DEPENDS += "libmcrypt"
EXTRA_OECONF += "--with-mcrypt=${STAGING_DIR_TARGET}${exec_prefix} \
                 " 
