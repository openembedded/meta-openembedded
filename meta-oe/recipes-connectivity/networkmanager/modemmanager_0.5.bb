DEPENDS = "ppp udev glib-2.0"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit gnome gettext

SRC_URI = "${GNOME_MIRROR}/ModemManager/${@gnome_verdir("${PV}")}/ModemManager-${PV}.tar.bz2"
SRC_URI[md5sum] = "cd04109506e88bf4c4cd3e7ce0034c08"
SRC_URI[sha256sum] = "108de70537b1193634883fa6b3642b130a23f4c04d901cb15caeceb486af6152"

S = "${WORKDIR}/ModemManager-${PV}"

FILES_${PN}-dbg += "${libdir}/ModemManager/.debug \
                    ${libdir}/pppd/*/.debug"
FILES_${PN}-dev += "${datadir}/dbus-1/interfaces \
                    ${libdir}/ModemManager/*a \
                    ${libdir}/pppd/*/*a"
FILES_${PN} += "${datadir}/dbus-1/*services/ \
                ${libdir}/ModemManager/*.so \
                ${libdir}/pppd/*/*.so \
                ${base_libdir}/udev"

RRECOMMENDS_${PN} += "ppp"
