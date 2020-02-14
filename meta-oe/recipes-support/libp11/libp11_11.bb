SUMMARY = "Library for using PKCS"
DESCRIPTION = "\
Libp11 is a library implementing a small layer on top of PKCS \
make using PKCS"
HOMEPAGE = "https://github.com/OpenSC/libp11"
BUGTRACKER = "https://github.com/OpenSC/libp11/issues"
SECTION = "Development/Libraries"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fad9b3332be894bab9bc501572864b29"
DEPENDS = "libtool openssl"

SRC_URI = "git://github.com/OpenSC/libp11.git"
SRCREV = "e1210903291b1de9eabcad26e740a4b2fbcca692"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-static"

do_install_append () {
    rm -rf ${D}${docdir}/${BPN}
}

FILES_${PN} += "${libdir}/engines*/pkcs11.so"
FILES_${PN}-dev += "${libdir}/engines*/libpkcs11${SOLIBSDEV}"

BBCLASSEXTEND = "native"
