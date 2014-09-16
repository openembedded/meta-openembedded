HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://README;md5=0854a4da34ac3990770794d771fac7fd"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"

inherit update-rc.d systemd
INITSCRIPT_NAME = "hostapd"

SYSTEMD_SERVICE_${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

DEFAULT_PREFERENCE = "-1"

SRC_URI = " \
    http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
    file://defconfig \
    file://init \
    file://hostapd.service \
"

S = "${WORKDIR}/hostapd-${PV}/hostapd"


do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -g -I${STAGING_INCDIR}/libnl3"
    make
}

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}/init.d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${S}/hostapd ${D}${sbindir}
    install -m 0755 ${S}/hostapd_cli ${D}${sbindir}
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
    install -m 0644 ${WORKDIR}/hostapd.service ${D}${systemd_unitdir}/system/
    sed -i -e 's,@SBINDIR@,${sbindir},g' -e 's,@SYSCONFDIR@,${sysconfdir},g' ${D}${systemd_unitdir}/system/hostapd.service
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"

SRC_URI[md5sum] = "23c1f78a693c3288802d516adb7fd289"
SRC_URI[sha256sum] = "f15b6bcb434378860ea5b88dffed7f54d8cb71fff2146de0f006977a5e25a882"
