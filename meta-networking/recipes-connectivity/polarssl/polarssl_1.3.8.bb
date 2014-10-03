SUMMARY = "Lightweight crypto and SSL/TLS library"
DESCRIPTION = "PolarSSL is a fork of the abandonned project XySSL. It \
is a lean crypto library providing SSL and TLS support \
in your programs."

HOMEPAGE = "https://polarssl.org"
BUGTRACKER = "https://github.com/polarssl/polarssl/issues"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

SECTION = "net"

SRC_URI = "https://polarssl.org/download/polarssl-${PV}-gpl.tgz"

SRC_URI[md5sum] = "d1a2b4f21727e888f143414d2e3144e6"
SRC_URI[sha256sum] = "318171db41335cacbb5b0047c94f1faf91442ab70a223b5223436703c9406ff1"

RDEPENDS_${PN} += "libcrypto"
EXTRA_OECMAKE = "-DUSE_SHARED_POLARSSL_LIBRARY=on"

inherit cmake
