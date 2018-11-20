SUMMARY = "A reliable logging library"
HOMEPAGE = "https://github.com/rsyslog/librelp"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=1fb9c10ed9fd6826757615455ca893a9"

DEPENDS = "gmp nettle libidn zlib gnutls"

SRC_URI = "git://github.com/rsyslog/librelp.git;protocol=https \
           file://0001-testbench-improvements.patch \
"

SRCREV = "4b6a81061bccf68cba6fddd27f99fb5dc0d0c3a3"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
