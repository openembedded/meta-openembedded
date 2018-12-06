SUMMARY = "wolfSSL Lightweight Embedded SSL/TLS Library"
DESCRIPTION = "wolfSSL, formerly CyaSSL, is a lightweight SSL library written \
               in C and optimized for embedded and RTOS environments. It can \
               be up to 20 times smaller than OpenSSL while still supporting \
               a full TLS client and server, up to TLS 1.3"
HOMEPAGE = "https://www.wolfssl.com/products/wolfssl"
BUGTRACKER = "https://github.com/wolfssl/wolfssl/issues"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "cyassl"
RPROVIDES_${PN} = "cyassl"

SRC_URI[md5sum] = "bc2dff70ba6e91aa3748d36ae6c6bc3d"
SRC_URI[sha256sum] = "f6c04c25355b340373931f43f8041b3cef78b61122e8a8edf9ee63c07b7a58be"
SRC_URI = "https://www.wolfssl.com/wolfssl-3.15.5.zip"

inherit autotools

BBCLASSEXTEND += "native nativesdk"
