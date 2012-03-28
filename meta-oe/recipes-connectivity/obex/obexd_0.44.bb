DESCRIPTION = "OBEX Server and Client"
DEPENDS = "openobex glib-2.0 dbus bluez4 libical"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "files://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PR = "r1"

SRC_URI = "http://www.kernel.org/pub/linux/bluetooth/obexd-${PV}.tar.gz"
SRC_URI[md5sum] = "3fb9cb9d7ab119753253ff784235a364"
SRC_URI[sha256sum] = "104fc50eb8bf41d690d31588c3a4829a75dc7738b1c76cf260171d8f525da758"

inherit autotools

PACKAGES =+ "obex-client obex-plugins"

FILES_${PN} += "${datadir}/dbus-1/services/${PN}.service"
FILES_obex-client = "${libexecdir}/obex-client \
                     ${datadir}/dbus-1/services/obex-client.service"
# currently the plugins are empty
FILES_obex-plugins = "${libdir}/obex/plugins"
