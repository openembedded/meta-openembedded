SECTION = "utils"
DESCRIPTION = "LVM2 is a set of utilities to manage logical volumes in Linux."
DEPENDS = "udev"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

S = "${WORKDIR}/LVM2.${PV}"
SRC_URI = "ftp://sources.redhat.com/pub/lvm2/old/LVM2.${PV}.tgz \
           file://lvm.conf \
          "

PACKAGECONFIG ??= "readline"
PACKAGECONFIG[readline] = "--enable-readline,--disable-readline,readline"

# Unset user/group to unbreak install.
EXTRA_OECONF = "--with-user= \
                --with-group= \
                --disable-o_direct \
                --enable-realtime \
                --enable-applib \
                --enable-cmdlib \
                --enable-udev_sync \
                --enable-udev_rules \
                --enable-pkgconfig \
                --enable-dmeventd \
                --enable-lvmetad \
                --with-udev-prefix= \
                --with-usrlibdir=${libdir} \
                --with-systemdsystemunitdir=${systemd_system_unitdir} \
"

PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'selinux', 'selinux', '', d)}"

PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux,"

inherit autotools pkgconfig systemd

do_install_append() {
    # Install machine specific configuration file
    install -m 0644 ${WORKDIR}/lvm.conf ${D}${sysconfdir}/lvm/lvm.conf
    sed -i -e 's:@libdir@:${libdir}:g' ${D}${sysconfdir}/lvm/lvm.conf
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        oe_runmake 'DESTDIR=${D}' install install_systemd_units
        sed -i -e 's:/usr/bin/true:${base_bindir}/true:g' ${D}${systemd_system_unitdir}/blk-availability.service
    else
        oe_runmake 'DESTDIR=${D}' install install_initscripts
        mv ${D}${sysconfdir}/rc.d/init.d ${D}${sysconfdir}/init.d
        rm -rf ${D}${sysconfdir}/rc.d
    fi
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "lvm2-monitor.service dm-event.socket dm-event.service lvm2-lvmetad.socket \
                         lvm2-pvscan@.service blk-availability.service"
SYSTEMD_AUTO_ENABLE = "disable"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += "${libdir}/device-mapper/*.so ${base_libdir}/udev"
FILES_${PN}-dbg += "${libdir}/device-mapper/.debug"

RDEPENDS_${PN} = "bash"

CONFFILES_${PN} += "${sysconfdir}/lvm/lvm.conf"
