SUMMARY = "Crypt Openssl Random cpan module"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

LIC_FILES_CHKSUM = "file://LICENSE;md5=385c55653886acac3821999a3ccd17b3"

SRC_URI = "http://www.cpan.org/modules/by-module/Crypt/Crypt-OpenSSL-Random-${PV}.tar.gz "
SRC_URI[md5sum] = "c56ac5dbdd46122eb9b8da59613b7b0a"
SRC_URI[sha256sum] = "acf7eb81023cd1f40d8c60b893096d041513df2be2aefe145cc7ae1a3dcc78c7"

S = "${WORKDIR}/Crypt-OpenSSL-Random-${PV}"

DEPENDS += " openssl \
"
inherit cpan
