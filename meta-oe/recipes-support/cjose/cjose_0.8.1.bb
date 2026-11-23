SUMMARY = "C library implementing the Javascript Object Signing and Encryption (JOSE)"
HOMEPAGE = "https://github.com/cisco/cjose"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7249e2f9437adfb8c88d870438042f0e"

SRC_URI = "git://github.com/cisco/cjose;protocol=https;branch=0.8.x;tag=${PV}"

SRCREV = "6fdb5fe75a86acb9cd34ca2eb13d9e090a6df588"

UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>\d+(\.\d+)+)$"

DEPENDS = "openssl libcheck jansson"

inherit pkgconfig autotools

