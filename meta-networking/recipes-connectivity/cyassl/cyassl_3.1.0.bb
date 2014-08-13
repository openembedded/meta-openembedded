SUMMARY = "CyaSSL Embedded SSL Library"
DESCRIPTION = "CyaSSL is a lightweight SSL library written in C and \
               optimized for embedded and RTOS environments. It can be \
               Up to 20 times smaller than OpenSSL while still supporting \
               a full TLS 1.2 client and server."
HOMEPAGE = "http://www.yassl.com/yaSSL/Products-cyassl.html"
BUGTRACKER = "http://github.com/cyassl/cyassl/issues"
SECTION = "libs/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://www.yassl.com/${BP}.zip"

SRC_URI[md5sum] = "72477eb50697e759d309c49cb1aef18a"
SRC_URI[sha256sum] = "85a79009d2a7468910dfb1e4b975e0b00a42c695f4f1a2adc16d2507b5d0b03e"

inherit autotools
