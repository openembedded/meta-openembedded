DESCRIPTION = "A storage daemon that implements well-defined D-Bus interfaces that can be used to query and manipulate storage devices."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=73d83aebe7e4b62346afde80e0e94273"

DEPENDS = "libatasmart sg3-utils polkit udev dbus-glib glib-2.0"
# optional dependencies: device-mapper parted

SRC_URI = "http://hal.freedesktop.org/releases/${BPN}-${PV}.tar.gz;name=${BPN} \
           file://optional-depends.patch"

inherit autotools

FILES_${PN} += "${libdir}/polkit-1/extensions/*.so \
                ${datadir}/dbus-1/ \
                ${datadir}/polkit-1 \
                ${base_libdir}/udev/* \
"

FILES_${PN}-dbg += "${base_libdir}/udev/.debug"


SRC_URI[udisks.md5sum] = "f630eefa51b95d6485eb3246bc1bea9a"
SRC_URI[udisks.sha256sum] = "7dc1a150a6a31b2727144603fa5a8f9852696fc5bdc4a11917b9b0f1b8e3dcf1"

