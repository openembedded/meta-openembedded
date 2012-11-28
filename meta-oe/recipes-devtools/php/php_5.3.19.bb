require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=cb564efdf78cce8ea6e4b5a4f7c05d97"

PR = "${INC_PR}.0"

SRC_URI += "file://acinclude-xml2-config.patch \
            file://php-m4-divert.patch \
            file://0001-php-don-t-use-broken-wrapper-for-mkdir.patch"

SRC_URI_append_pn-php += "file://iconv.patch \
            file://imap-fix-autofoo.patch \
            file://pear-makefile.patch \
            file://phar-makefile.patch \
            file://php_exec_native.patch \
            file://fix-fpm-cross-compile.patch \
            file://php-fpm.conf \
            file://php-fpm-apache.conf \
            "

SRC_URI[md5sum] = "e1d2a3ec7849d4b3032bd1abf1916aa4"
SRC_URI[sha256sum] = "814ce55b950158c4ddadbcd0b2d7d8cb4b570de4a29b9db021020594e2469fd6"
