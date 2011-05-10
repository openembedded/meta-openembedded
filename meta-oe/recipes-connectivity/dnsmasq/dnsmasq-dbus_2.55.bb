# dnsmasq with support for DBus interface

require dnsmasq_${PV}.bb

S = "${WORKDIR}/dnsmasq-${PV}"

DEPENDS = "dbus"
EXTRA_OEMAKE = "COPTS=-DHAVE_DBUS"

do_install_append () {
        install -d ${D}${sysconfdir}/dbus-1/system.d
        install -m 644 dbus/dnsmasq.conf ${D}${sysconfdir}/dbus-1/system.d/
}
