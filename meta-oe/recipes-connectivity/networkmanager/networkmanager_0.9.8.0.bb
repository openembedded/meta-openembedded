DESCRIPTION = "NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b"

DEPENDS = "libnl dbus dbus-glib udev wireless-tools gnutls util-linux ppp"

inherit gnome gettext systemd

SRC_URI = " \
    ${GNOME_MIRROR}/NetworkManager/${@gnome_verdir("${PV}")}/NetworkManager-${PV}.tar.xz \
    file://0001-don-t-try-to-run-sbin-dhclient-to-get-the-version-nu.patch \
    file://0002-NetworkManager.service-remove-reference-to-NetworkMa.patch \
"
SRC_URI[md5sum] = "38d28f6bd9220d85dfff47210706195c"
SRC_URI[sha256sum] = "c366bcded6354d8186ad93c05d26d6a20bc550aa0391f974704e7a60e9f6096b"


S = "${WORKDIR}/NetworkManager-${PV}"

EXTRA_OECONF = " \
    --enable-ifupdown \
    --disable-ifcfg-rh \
    --disable-ifnet \
    --disable-ifcfg-suse \
    --with-netconfig \
    --with-crypto=gnutls \
    --disable-more-warnings \
    --with-dhclient=${base_sbindir}/dhclient \
    --with-iptables=${sbindir}/iptables \
    --with-tests \
"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)}"
PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd --enable-polkit, \
    --without-systemdsystemunitdir, \
    polkit \
"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[concheck] = "--enable-concheck,--disable-concheck,libsoup-2.4"

# Work around dbus permission problems since we lack a proper at_console
do_install_prepend() {
    sed -i 's:deny send_destination:allow send_destination:g' ${S}/src/org.freedesktop.NetworkManager.conf
    sed -i 's:deny send_destination:allow send_destination:g' ${S}/callouts/nm-dispatcher.conf
    sed -i 's:deny send_destination:allow send_destination:g' ${S}/callouts/nm-dhcp-client.conf
    sed -i 's:deny send_destination:allow send_destination:g' ${S}/callouts/nm-avahi-autoipd.conf
}

do_install_append () {
    install -d ${D}${sysconfdir}/dbus-1/event.d
    # Additional test binaries
    install -d ${D}${bindir}
    install -m 0755 ${S}/test/.libs/libnm* ${D}${bindir}

    # Install an empty VPN folder as nm-connection-editor will happily segfault without it :o.
    # With or without VPN support built in ;).
    install -d ${D}${sysconfdir}/NetworkManager/VPN

    rm -rf "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

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
    ${systemd_unitdir}/system/NetworkManager-wait-online.service \
"

RRECOMMENDS_${PN} += "iptables"
RCONFLICTS_${PN} = "connman"
RDEPENDS_${PN} = " \
    wpa-supplicant \
    dhcp-client \
    ${@base_contains('COMBINED_FEATURES', '3gmodem', 'ppp', '', d)} \
"

FILES_${PN}-dbg += " \
    ${libdir}/NetworkManager/.debug/ \
    ${libdir}/pppd/*/.debug/ \
"

FILES_${PN}-dev += " \
    ${datadir}/NetworkManager/gdb-cmd \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*.la \
"

FILES_${PN}-tests = " \
    ${bindir}/nm-tool \
    ${bindir}/libnm-glib-test \
    ${bindir}/nm-online \
"

FILES_${PN}-bash-completion = "${datadir}/bash-completion"

SYSTEMD_SERVICE_${PN} = "NetworkManager.service"
