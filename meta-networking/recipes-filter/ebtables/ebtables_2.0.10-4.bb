SUMMARY = "Utility for basic Ethernet frame filtering on a Linux bridge, MAC NAT and brouting."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=53b4a999993871a28ab1488fdbd2e73e"
SECTION = "console/network"
PR = "r3"

RDEPENDS_${PN} += "perl"

RRECOMMENDS_${PN} += "kernel-module-ebtables \
    "

SRC_URI = "${SOURCEFORGE_MIRROR}/ebtables/ebtables-v${PV}.tar.gz \
           file://installnonroot.patch \
           file://01debian_defaultconfig.patch \
           file://ebtables.init \
           file://no-as-needed.patch \
"

SRC_URI[md5sum] = "506742a3d44b9925955425a659c1a8d0"
SRC_URI[sha256sum] = "dc6f7b484f207dc712bfca81645f45120cb6aee3380e77a1771e9c34a9a4455d"

S = "${WORKDIR}/ebtables-v${PV}"

inherit update-rc.d

EXTRA_OEMAKE = " \
        BINDIR=${base_sbindir} \
        MANDIR=${mandir} \
        ETHERTYPESPATH=${sysconfdir} \
        INITDIR=${sysconfdir}/init.d \
        SYSCONFIGDIR=${sysconfdir}/default \
        LIBDIR=${base_libdir}/ebtables \
        'CC=${CC}' \
        'CFLAGS=${CFLAGS}' \
        'LDFLAGS=${LDFLAGS} -Wl,--no-as-needed' \
        'LD=${LD}' \
"

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/default
    install -d ${D}${sysconfdir}/ebtables
    oe_runmake DESTDIR='${D}' install
    install -m 0755 ${WORKDIR}/ebtables.init ${D}/${sysconfdir}/init.d/ebtables
    mv ${D}${sysconfdir}/default/ebtables-config ${D}${sysconfdir}/default/ebtables

    # Fix hardcoded paths in scripts
    sed -i 's!/sbin/!${base_sbindir}/!g' ${D}/${sysconfdir}/init.d/ebtables
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}/${sysconfdir}/init.d/ebtables

    # The script ebtables-save refernces perl in exec_prefix, so
    # move it to sbindir to avoid QA issue
    install -d ${D}/${sbindir}
    mv ${D}/${base_sbindir}/ebtables-save ${D}/${sbindir}
}

CONFFILES_${PN} += "${sysconfdir}/default/ebtables"

INITSCRIPT_NAME = "ebtables"
INITSCRIPT_PARAMS = "start 41 S . stop 41 6 ."

FILES_${PN}-dbg += "${base_libdir}/ebtables/.debug"
FILES_${PN} += "${base_libdir}/ebtables/*.so"
