SECTION = "utils"
DESCRIPTION = "LVM2 is a set of utilities to manage logical volumes in Linux."
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12713b4d9386533feeb07d6e4831765a \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

DEPENDS += "util-linux"

SRC_URI = "ftp://sources.redhat.com/pub/lvm2/old/LVM2.${PV}.tgz \
           file://lvm.conf \
           file://0001-implement-libc-specific-reopen_stream.patch \
           file://0002-Guard-use-of-mallinfo-with-__GLIBC__.patch \
           file://0003-include-fcntl.h-for-O_-defines-and-fcntl-signature.patch \
           file://0004-tweak-MODPROBE_CMD-for-cross-compile.patch \
           file://0001-Avoid-bashisms-in-init-scripts.patch \
           "
S = "${WORKDIR}/LVM2.${PV}"

inherit autotools-brokensep pkgconfig systemd

LVM2_PACKAGECONFIG = "dmeventd lvmetad"
LVM2_PACKAGECONFIG_append_class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'selinux', d)} \
    thin-provisioning-tools \
    udev \
"

PACKAGECONFIG ??= "${LVM2_PACKAGECONFIG}"

# odirect is always enabled because there currently is a bug in
# lib/device/dev-io.c which prevents compiling without it. It is
# better to stick to configurations that were actually tested by
# upstream...
PACKAGECONFIG_append = " odirect"

PACKAGECONFIG[dmeventd] = "--enable-dmeventd,--disable-dmeventd"
PACKAGECONFIG[lvmetad] = "--enable-lvmetad,--disable-lvmetad"
PACKAGECONFIG[odirect] = "--enable-o_direct,--disable-o_direct"
PACKAGECONFIG[readline] = "--enable-readline,--disable-readline,readline"
PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux"
PACKAGECONFIG[thin-provisioning-tools] = "--with-thin=internal,--with-thin=none,,thin-provisioning-tools"
PACKAGECONFIG[udev] = "--enable-udev_sync --enable-udev_rules --with-udevdir=${nonarch_base_libdir}/udev/rules.d,--disable-udev_sync --disable-udev_rules,udev"

# Unset user/group to unbreak install.
EXTRA_OECONF = "--with-user= \
                --with-group= \
                --enable-realtime \
                --enable-applib \
                --enable-cmdlib \
                --enable-pkgconfig \
                --with-usrlibdir=${libdir} \
                --with-systemdsystemunitdir=${systemd_system_unitdir} \
                --disable-thin_check_needs_check \
                --with-thin-check=${sbindir}/thin_check \
                --with-thin-dump=${sbindir}/thin_dump \
                --with-thin-repair=${sbindir}/thin_repair \
                --with-thin-restore=${sbindir}/thin_restore \
"

CACHED_CONFIGUREVARS += "MODPROBE_CMD=${base_sbindir}/modprobe"

do_install_append() {
    # Install machine specific configuration file
    install -d ${D}${sysconfdir}/lvm
    install -m 0644 ${WORKDIR}/lvm.conf ${D}${sysconfdir}/lvm/lvm.conf
    sed -i -e 's:@libdir@:${libdir}:g' ${D}${sysconfdir}/lvm/lvm.conf
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        oe_runmake 'DESTDIR=${D}' install install_systemd_units
        sed -i -e 's:/usr/bin/true:${base_bindir}/true:g' ${D}${systemd_system_unitdir}/blk-availability.service
    else
        oe_runmake 'DESTDIR=${D}' install install_initscripts
        mv ${D}${sysconfdir}/rc.d/init.d ${D}${sysconfdir}/init.d
        rm -rf ${D}${sysconfdir}/rc.d
    fi
}

PACKAGE_BEFORE_PN = "${PN}-scripts libdevmapper libdevmapper-dev"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'dmeventd', 'lvm2-monitor.service dm-event.socket dm-event.service', '', d)} \
                         ${@bb.utils.contains('PACKAGECONFIG', 'lvmetad', 'lvm2-lvmetad.socket lvm2-pvscan@.service', '', d)} \
                         blk-availability.service"
SYSTEMD_AUTO_ENABLE = "disable"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += "${libdir}/device-mapper/*.so"
FILES_${PN}-scripts = " \
    ${sbindir}/blkdeactivate \
    ${sbindir}/fsadm \
    ${sbindir}/lvmconf \
    ${sbindir}/lvmdump \
"
# Specified explicitly for the udev rules, just in case that it does not get picked
# up automatically:
RDEPENDS_${PN}_append_class-target = " libdevmapper"
FILES_libdevmapper = "${sbindir}/dmsetup ${libdir}/libdevmapper.so.* ${nonarch_base_libdir}/udev/rules.d"
FILES_libdevmapper-dev = " \
    ${libdir}/libdevmapper.so \
    ${libdir}/pkgconfig/devmapper.pc \
    ${includedir}/libdevmapper.h \
"

RDEPENDS_${PN}-scripts = "${PN} (= ${EXTENDPKGV}) bash"
RDEPENDS_libdevmapper-dev = "libdevmapper (= ${EXTENDPKGV})"
RRECOMMENDS_${PN} = "${PN}-scripts (= ${EXTENDPKGV})"

CONFFILES_${PN} += "${sysconfdir}/lvm/lvm.conf"
