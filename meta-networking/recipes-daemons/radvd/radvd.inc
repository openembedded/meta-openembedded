SUMMARY = "IPv6 router advertisement daemon"
HOMEPAGE = "http://www.litech.org/radvd/"
SECTION = "console/network"
DEPENDS = "flex-native libdaemon"

# License is BSD-Style (with advertising clause) but also has an additional 0th clause
LICENSE = "radvd"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=73ebbf7182ae996e65e8fadc9a8c45db"

SRC_URI = "http://v6web.litech.org/radvd/dist/radvd-${PV}.tar.gz \
           file://radvd.init \
           file://volatiles.03_radvd"

inherit autotools update-rc.d useradd

do_install_append () {
    install -m 0755 -d ${D}${sysconfdir}/init.d \
                       ${D}${sysconfdir}/default/volatiles \
                       ${D}${docdir}/radvd
    # Install init script and volatiles
    install -m 0755 ${WORKDIR}/radvd.init ${D}${sysconfdir}/init.d/radvd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/radvd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/radvd
    sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/radvd
    sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${sysconfdir}/init.d/radvd

    install -m 0644 ${WORKDIR}/volatiles.03_radvd ${D}${sysconfdir}/default/volatiles/volatiles.03_radvd
    # Documentation
    for i in radvd.conf.example README; do \
        install -m 0644 $i ${D}${docdir}/radvd; \
    done
}

INITSCRIPT_NAME = "radvd"
INITSCRIPT_PARAMS = "defaults 20 80"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home ${localstatedir}/run/radvd/ -M -g nogroup radvd"

pkg_postinst_${PN} () {
    ${sysconfdir}/init.d/populate-volatile.sh update
}
