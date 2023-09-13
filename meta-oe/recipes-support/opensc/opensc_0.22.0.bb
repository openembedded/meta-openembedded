SUMMARY = "Smart card library and applications"
DESCRIPTION = "OpenSC is a tool for accessing smart card devices. Basic\
functionality (e.g. SELECT FILE, READ BINARY) should work on any ISO\
7816-4 compatible smart card. Encryption and decryption using private\
keys on the smart card is possible with PKCS\
such as the FINEID (Finnish Electronic IDentity) card. Swedish Posten\
eID cards have also been confirmed to work."

HOMEPAGE = "http://www.opensc-project.org/opensc/"
SECTION = "System Environment/Libraries"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=cb8aedd3bced19bd8026d96a8b6876d7"

#v0.21.0
SRCREV = "c902e1992195e00ada12d71beb1029287cd72037"
SRC_URI = "git://github.com/OpenSC/OpenSC;branch=master;protocol=https \
           file://CVE-2023-2977.patch \
          "

# CVE-2021-34193 is a duplicate CVE covering the 5 individual
# https://github.com/OpenSC/OpenSC/pull/2855/commits/7a049fc3922060fb75cb9fea9e58eef9edc357ae
CVE_CHECK_IGNORE += "CVE-2021-34193"

DEPENDS = "virtual/libiconv openssl"

S = "${WORKDIR}/git"
inherit autotools pkgconfig bash-completion

EXTRA_OECONF = " \
    --disable-static \
    --disable-ctapi \
    --disable-doc \
    --disable-strict \
"
EXTRA_OEMAKE = "DESTDIR=${D}"

PACKAGECONFIG ??= "pcsc"

PACKAGECONFIG[openct] = "--enable-openct,--disable-openct,openct"
PACKAGECONFIG[pcsc] = "--enable-pcsc,--disable-pcsc,pcsc-lite,pcsc-lite pcsc-lite-lib"

RDEPENDS:${PN} = "readline"

FILES:${PN} += "\
    ${libdir}/opensc-pkcs11.so \
    ${libdir}/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11-spy.so \
"
FILES:${PN}-dev += "\
    ${libdir}/pkcs11/opensc-pkcs11.so \
    ${libdir}/pkcs11/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11/pkcs11-spy.so \
"

BBCLASSEXTEND = "native"
