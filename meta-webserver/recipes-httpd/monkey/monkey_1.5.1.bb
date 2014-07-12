SUMMARY = "Fast and Lightweight HTTP Server for Linux"
HOMEPAGE = "http://monkey-project.com"
BUGTRACKER = "https://github.com/monkey/monkey/issues"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

SECTION = "net"

SRC_URI = "http://monkey-project.com/releases/1.5/monkey-${PV}.tar.gz \
           file://monkey.service \
           file://monkey.init"

SRC_URI[md5sum] = "2fe04135728f5c3a86c3a412059e0da3"
SRC_URI[sha256sum] = "28dfc5e57bbcd305727e7af6a1a0587868db1c1286498757bfeb72edddf457e7"

EXTRA_OECONF = " \
             --plugdir=${libdir}/monkey/ \
             --logdir=${localstatedir}/log/monkey/ \
             --pidfile=${localstatedir}/run/monkey.pid \
             --default-user=www-data \
             --datadir=${localstatedir}/www/monkey/ \
             --sysconfdir=${sysconfdir}/monkey/ \
             --enable-plugins=* \
             --disable-plugins=polarssl \
             --debug \
             --malloc-libc"

inherit autotools-brokensep pkgconfig update-rc.d systemd

INITSCRIPT_NAME = "monkey"
INITSCRIPT_PARAMS = "defaults 70"

SYSTEMD_SERVICE_${PN} = "monkey.service"

FILES_${PN} += "${localstatedir}/www/monkey/"

# temporarily remove, because ipk doesn't allow directories in CONFFILES
# CONFFILES_${PN} = "${sysconfdir}/monkey/"

do_install_append() {

    mkdir -p ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/monkey.init ${D}${sysconfdir}/init.d/monkey

    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/monkey.service ${D}/${systemd_unitdir}/system
    fi
}
