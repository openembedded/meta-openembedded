HOMEPAGE = "http://www.packagekit.org/"
DEPENDS = "policykit libgnome"
LICENSE = "LGPLv2"

PR = "r1"

SRC_URI = "http://hal.freedesktop.org/releases/polkit-gnome-${PV}.tar.bz2;name=polkitgnome"
SRC_URI[polkitgnome.md5sum] = "611cf39fba2945320fc7a9ec49087e69"
SRC_URI[polkitgnome.sha256sum] = "0d96c8032d658a2e1eef8adfbeafff0f569ef0cf68b35915423acc00873c8a92"

EXTRA_OECONF = " --disable-scrollkeeper \
                 --disable-man-pages \
                 --disable-examples \
                 --disable-gtk-doc \
                 --enable-introspection=no \
"

S = "${WORKDIR}/polkit-gnome-${PV}"

inherit autotools pkgconfig

FILES_${PN} += " ${datadir}/dbus-1 \
                 ${datadir}/PolicyKit \
               "

