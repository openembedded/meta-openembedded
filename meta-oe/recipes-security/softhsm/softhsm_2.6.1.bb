SUMMARY = "PKCS#11 HSM/Token Emulator"
HOMEPAGE = "https://www.opendnssec.org/softhsm/"
LICENSE = "BSD-2-Clause & ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef3f77a3507c3d91e75b9f2bdaee4210"

DEPENDS = "sqlite3"

SRC_URI = "git://github.com/softhsm/SoftHSMv2.git;protocol=https;branch=develop"
SRCREV = "7f99bedae002f0dd04ceeb8d86d59fc4a68a69a0"
S = "${WORKDIR}/git"

inherit autotools pkgconfig siteinfo

EXTRA_OECONF += " --with-sqlite3=${STAGING_DIR_HOST}/usr"
EXTRA_OECONF += "${@oe.utils.conditional('SITEINFO_BITS', '64', ' --enable-64bit', '', d)}"

PACKAGECONFIG ?= "ecc eddsa pk11 openssl"

PACKAGECONFIG[npm] = ",--disable-non-paged-memory"
PACKAGECONFIG[ecc] = "--enable-ecc,--disable-ecc"
PACKAGECONFIG[gost] = "--enable-gost,--disable-gost"
PACKAGECONFIG[eddsa] = "--enable-eddsa, --disable-eddsa"
PACKAGECONFIG[fips] = "--enable-fips, --disable-fips"
PACKAGECONFIG[notvisable] = "--disable-visibility"
PACKAGECONFIG[openssl] = "--with-openssl=${STAGING_DIR_HOST}/usr --with-crypto-backend=openssl, --without-openssl, openssl, openssl"
PACKAGECONFIG[botan] = "--with-botan=${STAGING_DIR_HOST}/usr --with-crypto-backend=botan, --without-botan, botan"
PACKAGECONFIG[migrate] = "--with-migrate"
PACKAGECONFIG[pk11] = "--enable-p11-kit --with-p11-kit==${STAGING_DIR_HOST}/usr, --without-p11-kit, p11-kit, p11-kit"

RDEPENDS:${PN} = "sqlite3"
BBCLASSEXTEND = "native nativesdk"
