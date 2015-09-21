SUMMARY = "ModemManager is a daemon controlling broadband devices/connections"
DESCRIPTION = "ModemManager is a DBus-activated daemon which controls mobile broadband (2G/3G/4G) devices and connections"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/ModemManager/"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

inherit autotools gettext gtk-doc systemd

DEPENDS = "glib-2.0 libmbim libqmi polkit libgudev dbus-glib"

SRC_URI = "http://www.freedesktop.org/software/ModemManager/ModemManager-${PV}.tar.xz"
SRC_URI[md5sum] = "fe74eaa9a77e51e552b4356b4c7195cb"
SRC_URI[sha256sum] = "07d332fbe6f45f788f740810951e04042aaf023bd5ecf8ef0b3a06e37703585d"

S = "${WORKDIR}/ModemManager-${PV}"

FILES_${PN} += " \
    ${datadir}/icons \
    ${datadir}/polkit-1 \
    ${libdir}/ModemManager \
    ${systemd_unitdir}/system \
"

FILES_${PN}-dev += " \
    ${datadir}/dbus-1 \
    ${libdir}/ModemManager/*.la \
"

FILES_${PN}-staticdev += " \
    ${libdir}/ModemManager/*.a \
"

FILES_${PN}-dbg += "${libdir}/ModemManager/.debug"

SYSTEMD_SERVICE_${PN} = "ModemManager.service"
# no need to start on boot - dbus will start on demand
SYSTEMD_AUTO_ENABLE = "disable"
