SUMMARY = "C library implementing the Javascript Object Signing and Encryption (JOSE)"
HOMEPAGE = "https://github.com/OpenIDC/cjose"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7249e2f9437adfb8c88d870438042f0e"

SRC_URI = "git://github.com/OpenIDC/cjose;protocol=https;branch=version-0.6.2.x;tag=v${PV} \
           file://0001-jwk-initialize-the-decoded-buffer-lengths-up-front.patch \
           "

SRCREV = "10af8915a666b50caa5500cdc3f2523b916be720"

DEPENDS = "openssl libcheck jansson"

inherit pkgconfig autotools

