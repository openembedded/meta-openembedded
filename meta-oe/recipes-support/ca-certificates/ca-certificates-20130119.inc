DESCRIPTION = "Common CA certificates"
HOMEPAGE = "http://packages.debian.org/sid/ca-certificates"
SECTION = "misc"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=6135800ff6d893c7904d7aad90972eb5"
PR = "r1"

SRC_URI = "${DEBIAN_MIRROR}/main/c/ca-certificates/ca-certificates_${PV}.tar.gz \
           file://0001-update-ca-certificates-remove-c-rehash.patch \
           file://0002-update-ca-certificates-use-SYSROOT.patch"

SRC_URI[md5sum] = "1fbbec2028a33cf865b79c204aa2e626"
SRC_URI[sha256sum] = "fb51ec9a2c551838cc1646a0b3c9e6bea5c1bb20ff5d71c542451fac4b191c22"

inherit allarch
