DESCRIPTION = "A storage daemon that implements well-defined D-Bus interfaces that can be used to query and manipulate storage devices."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=73d83aebe7e4b62346afde80e0e94273"

DEPENDS = "libatasmart sg3-utils polkit udev dbus-glib glib-2.0 intltool-native"
# optional dependencies: device-mapper parted

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

SRC_URI = "http://hal.freedesktop.org/releases/${BPN}-${PV}.tar.gz;name=${BPN} \
           file://optional-depends.patch"

SRC_URI += "${@base_contains('DISTRO_FEATURES', 'systemd', 'file://add-systemd-support.patch', '', d)}"

SRC_URI[udisks.md5sum] = "86c63b2b5484f2060499a052b5b6256b"
SRC_URI[udisks.sha256sum] = "854b89368733b9c3a577101b761ad5397ae75a05110c8698ac5b29de9a8bf8f5"

PR = "r9"

inherit autotools systemd gtk-doc

PACKAGECONFIG ??= ""
PACKAGECONFIG[parted] = "--enable-parted,--disable-parted,parted"

EXTRA_OECONF = "--disable-man-pages"

FILES_${PN} += "${libdir}/polkit-1/extensions/*.so \
                ${datadir}/dbus-1/ \
                ${datadir}/polkit-1 \
                ${base_libdir}/udev/* \
"

FILES_${PN}-dbg += "${base_libdir}/udev/.debug"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "udisks-daemon.service"
SYSTEMD_AUTO_ENABLE = "disable"
