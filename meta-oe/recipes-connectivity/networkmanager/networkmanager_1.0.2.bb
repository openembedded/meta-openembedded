SUMMARY = "NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b"

DEPENDS = "libnl dbus dbus-glib udev wireless-tools nss util-linux libndp"

inherit gnome gettext systemd

SRC_URI = " \
    ${GNOME_MIRROR}/NetworkManager/${@gnome_verdir("${PV}")}/NetworkManager-${PV}.tar.xz \
    file://0001-don-t-try-to-run-sbin-dhclient-to-get-the-version-nu.patch \
"
SRC_URI[md5sum] = "5a4899f89edcbdd1ac46d2d947e8d6eb"
SRC_URI[sha256sum] = "359385707494bedbb48cfe0992ccfbcc4ac147dae1f7a47055c71e96439508ff"

S = "${WORKDIR}/NetworkManager-${PV}"

EXTRA_OECONF = " \
    --enable-ifupdown \
    --disable-ifcfg-rh \
    --disable-ifnet \
    --disable-ifcfg-suse \
    --with-netconfig \
    --with-crypto=nss \
    --disable-more-warnings \
    --with-dhclient=${base_sbindir}/dhclient \
    --with-iptables=${sbindir}/iptables \
    --with-tests \
    --with-dnsmasq=${bindir}/dnsmasq \
"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)}"
PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd --enable-polkit, \
    --without-systemdsystemunitdir, \
    polkit \
"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
PACKAGECONFIG[bluez5] = "--enable-bluez5-dun,--disable-bluez5-dun,bluez5"
PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[concheck] = "--with-libsoup=yes,--with-libsoup=no,libsoup-2.4"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1=yes,--with-modem-manager-1=no,modemmanager"
PACKAGECONFIG[ppp] = "--enable-ppp,--disable-ppp,ppp"

PACKAGES =+ "libnmutil libnmglib libnmglib-vpn ${PN}-tests ${PN}-bash-completion"

FILES_libnmutil += "${libdir}/libnm-util.so.*"
FILES_libnmglib += "${libdir}/libnm-glib.so.*"
FILES_libnmglib-vpn += "${libdir}/libnm-glib-vpn.so.*"

FILES_${PN} += " \
    ${libexecdir} \
    ${libdir}/pppd/*/nm-pppd-plugin.so \
    ${libdir}/NetworkManager/*.so \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${base_libdir}/udev/* \
    ${systemd_unitdir}/system \
"

RRECOMMENDS_${PN} += "iptables dnsmasq"
RCONFLICTS_${PN} = "connman"
RDEPENDS_${PN} = " \
    wpa-supplicant \
    dhcp-client \
"

FILES_${PN}-dbg += " \
    ${libdir}/NetworkManager/.debug/ \
    ${libdir}/pppd/*/.debug/ \
"

FILES_${PN}-dev += " \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*.la \
"

FILES_${PN}-tests = " \
    ${bindir}/nm-online \
"

FILES_${PN}-bash-completion = "${datadir}/bash-completion"

SYSTEMD_SERVICE_${PN} = "NetworkManager.service"
