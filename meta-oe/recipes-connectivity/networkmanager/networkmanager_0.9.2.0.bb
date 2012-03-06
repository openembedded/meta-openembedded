DESCRIPTION = "NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b"

PR = "r5"

DEPENDS = "systemd libnl dbus dbus-glib udev wireless-tools polkit gnutls util-linux ppp"
inherit gnome gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager/${@gnome_verdir("${PV}")}/NetworkManager-${PV}.tar.bz2 \
    file://0001-don-t-try-to-run-sbin-dhclient-to-get-the-version-nu.patch \
    file://gtk-doc.make \
"
SRC_URI[md5sum] = "bc0b00b8a187762d93c50a9706b4c5c3"
SRC_URI[sha256sum] = "a178ed2f0b5a1045ec47b217ea531d0feba9208f6bcfe64b701174a5c1479816"


S = "${WORKDIR}/NetworkManager-${PV}"

EXTRA_OECONF = " \
		--with-distro=debian \
		--with-crypto=gnutls \
		--disable-more-warnings \
                --with-dhclient=${base_sbindir}/dhclient \
                --with-iptables=${sbindir}/iptables \
                --with-tests \
"

do_configure_prepend() {
    cp ${WORKDIR}/gtk-doc.make ${S}/
    echo "EXTRA_DIST = version.xml" > gnome-doc-utils.make
    sed -i -e 's:man \\:man:' -e s:docs::g ${S}/Makefile.am
    sed -i -e /^docs/d ${S}/configure.ac
}

# Work around dbus permission problems since we lack a proper at_console
do_install_prepend() {
	sed -i -e s:deny:allow:g ${S}/src/NetworkManager.conf
	sed -i -e s:deny:allow:g ${S}/callouts/nm-dispatcher.conf
}

do_install_append () {
	install -d ${D}/etc/dbus-1/event.d
	# Additional test binaries
	install -d ${D}/usr/bin
	install -m 0755 ${S}/test/.libs/libnm* ${D}/usr/bin

	install -d ${D}/etc/NetworkManager/

	# Install an empty VPN folder as nm-connection-editor will happily segfault without it :o.
	# With or without VPN support built in ;).
	install -d ${D}/etc/NetworkManager/VPN
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE = "NetworkManager.service"

PACKAGES =+ "libnmutil libnmglib libnmglib-vpn ${PN}-tests" 

FILES_libnmutil += "${libdir}/libnm-util.so.*"
FILES_libnmglib += "${libdir}/libnm_glib.so.*"
FILES_libnmglib-vpn += "${libdir}/libnm_glib_vpn.so.*"

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
RDEPENDS_${PN} = "wpa-supplicant dhcp-client \
           ${@base_contains('COMBINED_FEATURES', '3gmodem', 'ppp', '', d)} \
           "

FILES_${PN}-dbg += "${libdir}/NetworkManager/.debug/ \
		    ${libdir}/pppd/*/.debug/ "

FILES_${PN}-dev += "${datadir}/NetworkManager/gdb-cmd \
                    ${libdir}/pppd/*/*.la \
                    ${libdir}/NetworkManager/*.la"

FILES_${PN}-tests = "${bindir}/nm-tool \
                     ${bindir}/libnm_glib_test \
                     ${bindir}/nm-online"
