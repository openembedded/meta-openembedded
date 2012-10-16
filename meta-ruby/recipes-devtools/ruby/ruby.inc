DESCRIPTION = "Ruby is an interpreted scripting language \
for quick and easy object-oriented programming."
SECTION = "devel/ruby"
LICENSE = "GPLv2 & LGPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=99f88e67b65504d289a0326c59f9c148 \
                    file://LEGAL;md5=7d502ae3b621dc406b220dadddb72e16 \
                    file://LGPL;md5=7fbc338309ac38fefcd64b04bb903e34 \
"

DEPENDS = "ruby-native zlib openssl"
DEPENDS_virtclass-native = ""

INC_PR = "r0"

SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
SRC_URI = "ftp://ftp.ruby-lang.org/pub/ruby/${SHRT_VER}/ruby-${PV}.tar.gz \
           file://extmk_run.patch \
           file://extmk.patch \
"

S = "${WORKDIR}/ruby-${PV}"

inherit autotools

# fix building of socket extension
EXTRA_OECONF = "--enable-wide-getaddrinfo"

# This snippet lets compiled extensions which rely on external libraries,
# such as zlib, compile properly.  If we don't do this, then when extmk.rb
# runs, it uses the native libraries instead of the target libraries, and so
# none of the linking operations succeed -- which makes extconf.rb think
# that the libraries aren't available and hence that the extension can't be
# built.

do_configure_prepend() {
	sed -i "s#%%TARGET_CFLAGS%%#$TARGET_CFLAGS#; s#%%TARGET_LDFLAGS%%#$TARGET_LDFLAGS#" ${S}/common.mk
	rm -rf ${S}/ruby/
}
