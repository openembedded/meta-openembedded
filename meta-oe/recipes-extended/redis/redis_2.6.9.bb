SUMMARY = "Redis key-value store"
DESCRIPTION = "Redis is an open source, advanced key-value store."
HOMEPAGE = "http://redis.io"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM="file://COPYING;md5=673e0ac66aac758f8f2140c6fc7947d2"

SRC_URI = "http://redis.googlecode.com/files/redis-${PV}.tar.gz \
           file://hiredis-use-default-CC-if-it-is-set.patch \
           file://lua-update-Makefile-to-use-environment-build-setting.patch \
           file://oe-use-libc-malloc.patch \
           file://redis.conf \
           file://init-redis-server \
"

inherit update-rc.d

INITSCRIPT_NAME = "redis-server"
INITSCRIPT_PARAMS = "defaults 87"

SRC_URI[md5sum] = "5093fb7c5f763e828c857daf260665bc"
SRC_URI[sha256sum] = "4d967eff2038aebea33875d17e85ed67179df6505df68529a622f7836d1c7489"

do_install() {
    export PREFIX=${D}/${prefix}
    oe_runmake install

    install -d ${D}/${sysconfdir}/redis
    install -m 0755 ${WORKDIR}/redis.conf ${D}/${sysconfdir}/redis/redis.conf

    install -d ${D}/${sysconfdir}/init.d 
    install -m 0755 ${WORKDIR}/init-redis-server ${D}/${sysconfdir}/init.d/redis-server

    install -d ${D}/var/lib/redis/
}

