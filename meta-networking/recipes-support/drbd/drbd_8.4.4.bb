SUMMARY = "Distributed block device driver for Linux"
DESCRIPTION = "DRBD mirrors a block device over the network to another machine.\
DRBD mirrors a block device over the network to another machine.\
Think of it as networked raid 1. It is a building block for\
setting up high availability (HA) clusters."
HOMEPAGE = "http://www.drbd.org/"
SECTION = "kernel/userland"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=5574c6965ae5f583e55880e397fbb018"

SRC_URI = "http://oss.linbit.com/${BPN}/8.4/${BPN}-${PV}.tar.gz \
           file://drbd.service \
          "
SRC_URI[md5sum] = "b51815343c1a9151e2936b3b97520388"
SRC_URI[sha256sum] = "a056219c5c23b079c3354179f7a1b9f55d47e573a4cd3178f2ef4c15604288f0"

SYSTEMD_SERVICE_${PN} = "drbd.service"
SYSTEMD_AUTO_ENABLE = "disable"

inherit autotools-brokensep systemd

EXTRA_OECONF = "--with-utils                  \
                --without-km                  \
                --with-initdir=/etc/init.d    \
                --without-pacemaker           \
                --without-rgmanager           \
                --without-bashcompletion      \
                --with-distro debian          \
               "

do_configure (){
    oe_runconf
}

do_install_append() {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}/${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/drbd.service ${D}/${systemd_unitdir}/system
        install -d ${D}/${libexecdir}
        install -m 755 ${D}/${sysconfdir}/init.d/drbd ${D}/${libexecdir}/drbd-helper

        sed -i -e 's,@LIBEXECDIR@,${libexecdir},g' \
            ${D}${systemd_unitdir}/system/drbd.service
    fi
}

RDEPENDS_${PN} += "bash perl"

FILES_${PN} += "/run"
FILES_${PN} += "${base_libdir}/drbd"
FILES_${PN}-dbg += "${base_libdir}/drbd/.debug"
