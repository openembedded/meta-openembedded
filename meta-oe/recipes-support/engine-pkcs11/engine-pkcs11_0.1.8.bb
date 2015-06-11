SUMMARY = "A PKCS"
DESCRIPTION = "\
Engine_pkcs11 is an implementation of an engine for OpenSSL. It can be \
loaded using code, config file or command line and will pass any function \
call by openssl to a PKCS cards and software for using smart cards in PKCS"
HOMEPAGE = "https://github.com/OpenSC/engine_pkcs11"
SECTION = "Development/Libraries"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://doc/README;md5=d2ab6bf8854463fa6bf98f5bb6dfc47c"
DEPENDS = "openssl libp11"

SRC_URI = "git://github.com/OpenSC/engine_pkcs11.git"
SRCREV = "bb775c32dba8cc4b4381a53da7ab5d7b22b7921d"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "\
    --disable-static \
    --libdir ${libdir}/openssl \
"

do_install_append () {
    rm -f ${D}${libdir}/openssl/engines/engine_pkcs11.la
}

FILES_${PN} += "${libdir}/openssl/engines/engine_pkcs11.so*"
FILES_${PN}-dbg += "${libdir}/openssl/engines/.debug/*"
