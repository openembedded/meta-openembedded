DEPENDS = "ppp udev glib-2.0 dbus-glib"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"


inherit gnomebase gettext

SRC_URI = "${GNOME_MIRROR}/ModemManager/${@gnome_verdir("${PV}")}/ModemManager-${PV}.tar.xz"

SRC_URI[md5sum] = "f32640f6672d997ec0887307186e9639"
SRC_URI[sha256sum] = "d4468300cf4aa7baf21c8564fa515e578056f34de5a64f452b053331f89e8ae2"

S = "${WORKDIR}/ModemManager-${PV}"

FILES_${PN}-dbg += "${libdir}/ModemManager/.debug \
                    ${libdir}/pppd/*/.debug"
FILES_${PN}-dev += "${datadir}/dbus-1/interfaces \
                    ${libdir}/pppd/*/*.la \
                    ${libdir}/ModemManager/*.la"
FILES_${PN}-staticdev += "\
                          ${libdir}/ModemManager/*.a \
                          ${libdir}/pppd/*/*.a"
FILES_${PN} += "${datadir}/dbus-1/*services/ \
                ${libdir}/ModemManager/*.so \
                ${libdir}/pppd/*/*.so \
                ${base_libdir}/udev"

RRECOMMENDS_${PN} += "ppp"
