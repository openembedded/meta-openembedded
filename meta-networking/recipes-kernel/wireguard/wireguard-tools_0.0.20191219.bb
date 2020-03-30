require wireguard.inc

LIC_FILES_CHKSUM = "file://../../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit bash-completion systemd pkgconfig

DEPENDS += "wireguard-module libmnl"

S = "${WORKDIR}/WireGuard-${PV}/src/tools"

do_install () {
    oe_runmake DESTDIR="${D}" PREFIX="${prefix}" SYSCONFDIR="${sysconfdir}" \
        SYSTEMDUNITDIR="${systemd_unitdir}" \
        WITH_SYSTEMDUNITS=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'yes', '', d)} \
        WITH_BASHCOMPLETION=yes \
        WITH_WGQUICK=yes \
        install
}

FILES_${PN} = " \
    ${sysconfdir} \
    ${systemd_unitdir} \
    ${bindir} \
"

RDEPENDS_${PN} = "wireguard-module bash"
