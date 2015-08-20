SUMMARY = "The Sodium crypto library"
HOMEPAGE = "http://libsodium.org/"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=092a09b78c3be486fac807435bf17b7a"

SRC_URI = "https://download.libsodium.org/libsodium/releases/${BPN}-${PV}.tar.gz"

SRC_URI[md5sum] = "b3bcc98e34d3250f55ae196822307fab"
SRC_URI[sha256sum] = "cbcfc63cc90c05d18a20f229a62c7e7054a73731d0aa858c0517152c549b1288"

inherit autotools
