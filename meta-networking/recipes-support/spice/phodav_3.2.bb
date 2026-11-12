SUMMARY = "phodav is a WebDav server implementation using libsoup (RFC 4918)."
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = " \
    glib-2.0 \
    libsoup \
"

PACKAGECONFIG ??= "avahi"
PACKAGECONFIG[avahi] = "-Davahi=enabled,-Davahi=disabled,avahi"

inherit pkgconfig gnomebase gettext

EXTRA_OEMESON += "-Dsystemdsystemunitdir=${systemd_system_unitdir} \
                  -Dsysusersdir=${nonarch_libdir}/sysusers.d \
                  -Dudevrulesdir=${nonarch_base_libdir}/udev/rules.d \
                  -Dintrospection=disabled \
                  -Ddocs=disabled \
                  -Dlitmus=disabled \
"

SRC_URI = "https://download.gnome.org/sources/phodav/${@oe.utils.trim_version('${PV}', 2)}/phodav-${PV}.tar.xz;name=archive"
SRC_URI[archive.sha256sum] = "93c993066ab3aebd5b539bab9d731eac43abbb750109c895f151ca3c74a5fc82"

FILES:${PN} += "${libdir} ${systemd_system_unitdir}"
