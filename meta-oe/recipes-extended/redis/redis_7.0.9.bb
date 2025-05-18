SUMMARY = "Redis key-value store"
DESCRIPTION = "Redis is an open source, advanced key-value store."
HOMEPAGE = "http://redis.io"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ffdd6c926faaece928cf9d9640132d2"
DEPENDS = "readline lua ncurses"

FILESPATH =. "${FILE_DIRNAME}/${BPN}-7:"

SRC_URI = "http://download.redis.io/releases/${BP}.tar.gz \
           file://redis.conf \
           file://init-redis-server \
           file://redis.service \
           file://hiredis-use-default-CC-if-it-is-set.patch \
           file://lua-update-Makefile-to-use-environment-build-setting.patch \
           file://oe-use-libc-malloc.patch \
           file://0001-src-Do-not-reset-FINAL_LIBS.patch \
           file://GNU_SOURCE-7.patch \
           file://0006-Define-correct-gregs-for-RISCV32.patch \
           "
SRC_URI[sha256sum] = "f77135c2a47c9151d4028bfea3b34470ab4d324d1484f79a84c6f32a3cfb9f65"

inherit autotools-brokensep update-rc.d systemd useradd

FINAL_LIBS:x86:toolchain-clang = "-latomic"
FINAL_LIBS:riscv32:toolchain-clang = "-latomic"
FINAL_LIBS:mips = "-latomic"
FINAL_LIBS:arm = "-latomic"
FINAL_LIBS:powerpc = "-latomic"

export FINAL_LIBS

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN}  = "--system --home-dir /var/lib/redis -g redis --shell /bin/false redis"
GROUPADD_PARAM:${PN} = "--system redis"

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "USE_SYSTEMD=yes,USE_SYSTEMD=no,systemd"

EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

do_compile:prepend() {
    (cd deps && oe_runmake hiredis lua linenoise)
}

do_install() {
    export PREFIX=${D}/${prefix}
    oe_runmake install
    install -d ${D}/${sysconfdir}/redis
    install -m 0644 ${WORKDIR}/redis.conf ${D}/${sysconfdir}/redis/redis.conf
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init-redis-server ${D}/${sysconfdir}/init.d/redis-server
    install -d ${D}/var/lib/redis/
    chown redis.redis ${D}/var/lib/redis/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/redis.service ${D}${systemd_system_unitdir}
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${systemd_system_unitdir}/redis.service

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        sed -i 's!daemonize yes!# daemonize yes!' ${D}/${sysconfdir}/redis/redis.conf
        sed -i 's!supervised no!supervised systemd!' ${D}/${sysconfdir}/redis/redis.conf
    fi
}

CONFFILES:${PN} = "${sysconfdir}/redis/redis.conf"

INITSCRIPT_NAME = "redis-server"
INITSCRIPT_PARAMS = "defaults 87"

SYSTEMD_SERVICE:${PN} = "redis.service"
