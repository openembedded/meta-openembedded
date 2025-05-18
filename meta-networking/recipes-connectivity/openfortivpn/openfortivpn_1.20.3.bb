SUMMARY = "Client for PPP+SSL VPN tunnel services"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3d575262a651a6f1a17210ce41bf907d"

SRC_URI = "git://github.com/adrienverge/openfortivpn.git;protocol=https;branch=master"
SRCREV = "45cb8e0f9984f1d54b648e499bda637d96568908"

DEPENDS = "openssl"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

PACKAGECONFIG[resolvconf] = "--with-resolvconf=${base_sbindir}/resolvconf --enable-resolvconf,--with-resolvconf=DISABLED,,"
PACKAGECONFIG[systemd] = "--with-systemdsystemunitdir=${systemd_system_unitdir},--without-systemdsystemunitdir,,"

S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd

EXTRA_OECONF = " \
    --with-pppd=${sbindir}/pppd \
    --disable-proc \
"

SYSTEMD_SERVICE:${PN} = "openfortivpn@.service"

RDEPENDS:${PN} = "ppp"
