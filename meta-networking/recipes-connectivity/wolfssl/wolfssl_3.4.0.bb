SUMMARY = "wolfSSL Lightweight, Embedded SSL Library"
DESCRIPTION = "wolfSSL, formerly CyaSSL, is a lightweight SSL library written in C and \
               optimized for embedded and RTOS environments. It can be \
               Up to 20 times smaller than OpenSSL while still supporting \
               a full TLS 1.2 client and server."
HOMEPAGE = "http://www.wolfssl.com/yaSSL/Products-wolfssl.html"
BUGTRACKER = "http://github.com/wolfssl/wolfssl/issues"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "cyassl"
RPROVIDES_${PN} = "cyassl"

SRC_URI = "http://www.wolfssl.com/${BP}.zip"

SRC_URI[md5sum] = "ec6ed4c5847454ac928215a46b3e0c33"
SRC_URI[sha256sum] = "bc49a142c17b1e52a126417e5d7cbadcb7f18f5c81e446c12e214483395159ea"

inherit autotools

