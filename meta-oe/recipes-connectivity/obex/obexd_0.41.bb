DESCRIPTION = "OBEX Server and Client"
DEPENDS = "openobex glib-2.0 dbus bluez4 libical"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "files://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

SRC_URI = "http://www.kernel.org/pub/linux/bluetooth/obexd-${PV}.tar.gz"
SRC_URI[md5sum] = "073a10634d19ecebeea93db815857054"
SRC_URI[sha256sum] = "edfa54f7a08d00f81ababe4267d15ec138e9c80dd76d0493dcff451374dac7be"

inherit autotools

FILES_${PN} += "${datadir}/dbus-1/"

