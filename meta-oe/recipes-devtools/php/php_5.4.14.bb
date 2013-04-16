require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=cb564efdf78cce8ea6e4b5a4f7c05d97"

PR = "${INC_PR}.0"

SRC_URI += "file://acinclude-xml2-config.patch \
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

SRC_URI[md5sum] = "cfdc044be2c582991a1fe0967898fa38"
SRC_URI[sha256sum] = "5450f3843bc651eb3fb00601f0cce1930aaaf65c7c966c02fe4a46f9c81be20a"
