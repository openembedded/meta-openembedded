SUMMARY = "Valkey key-value store"
DESCRIPTION = "A flexible distributed key-value datastore that supports both caching and beyond caching workloads."
HOMEPAGE = "http://valkey.io"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=08b9159809d809e8aaa340a8387e693e"
DEPENDS = "readline lua ncurses"

SRC_URI = " \
	git://github.com/valkey-io/valkey.git;branch=8.1;protocol=https \
        file://valkey.conf \
	file://init-valkey-server \
	file://valkey.service \
	file://hiredis-use-default-CC-if-it-is-set.patch \
	file://lua-update-Makefile-to-use-environment-build-setting.patch \
	file://oe-use-libc-malloc.patch \
	file://0001-src-Do-not-reset-FINAL_LIBS.patch \
	file://GNU_SOURCE-7.patch \
"
SRCREV = "67c8683792fc9ab7e295f833478eca180c5e4691"

S = "${WORKDIR}/git"

RPROVIDES:${PN} = "virtual-redis"

inherit pkgconfig update-rc.d systemd useradd

FINAL_LIBS:x86:toolchain-clang = "-latomic"
FINAL_LIBS:riscv32:toolchain-clang = "-latomic"
FINAL_LIBS:mips = "-latomic"
FINAL_LIBS:arm = "-latomic"
FINAL_LIBS:powerpc = "-latomic"

export FINAL_LIBS

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN}  = "--system --home-dir /var/lib/valkey -g valkey --shell /bin/false valkey"
GROUPADD_PARAM:${PN} = "--system valkey"

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "USE_SYSTEMD=yes,USE_SYSTEMD=no,systemd"

EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

do_compile() {
    oe_runmake -C deps hiredis lua linenoise
    oe_runmake
}

do_install() {
    export PREFIX=${D}/${prefix}
    oe_runmake install
    install -d ${D}/${sysconfdir}/valkey
    install -m 0644 ${UNPACKDIR}/valkey.conf ${D}/${sysconfdir}/valkey/valkey.conf
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/init-valkey-server ${D}/${sysconfdir}/init.d/valkey-server
    install -d ${D}/var/lib/valkey/
    chown valkey.valkey ${D}/var/lib/valkey/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/valkey.service ${D}${systemd_system_unitdir}
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${systemd_system_unitdir}/valkey.service

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        sed -i 's!daemonize yes!# daemonize yes!' ${D}/${sysconfdir}/valkey/valkey.conf
        sed -i 's!supervised no!supervised systemd!' ${D}/${sysconfdir}/valkey/valkey.conf
    fi
}

CONFFILES:${PN} = "${sysconfdir}/valkey/valkey.conf"

INITSCRIPT_NAME = "valkey-server"
INITSCRIPT_PARAMS = "defaults 87"

SYSTEMD_SERVICE:${PN} = "valkey.service"

CVE_STATUS[CVE-2022-3734] = "not-applicable-platform: CVE only applies for Windows."
