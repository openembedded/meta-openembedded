DESCRIPTION = "Minimalistic C client library for Redis"
HOMEPAGE = "http://github.com/redis/hiredis"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d84d659a35c666d23233e54503aaea51"
DEPENDS = "redis"

SRC_URI = "git://github.com/redis/hiredis;protocol=https;branch=master"
SRCREV = "c14775b4e48334e0262c9f168887578f4a368b5d"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ??= "ssl"
PACKAGECONFIG[ssl] = "-DENABLE_SSL=ON, -DENABLE_SSL=OFF, openssl"

FILES:${PN}-dev += "${datadir}/hiredis_ssl ${prefix}/build"
