DESCRIPTION = "Desktop containment framework."
HOMEPAGE = "http://flatpak.org"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = " \
    gitsm://github.com/flatpak/flatpak;protocol=https;nobranch=1 \
    file://0001-flatpak-pc-add-pc_sysrootdir.patch \
"

SRCREV = "e936e3100d406c50ba49f3ad6a0ecae455345ec0"

S = "${WORKDIR}/git"

inherit meson pkgconfig gettext systemd gobject-introspection python3native useradd mime features_check

REQUIRED_DISTRO_FEATURES = "polkit"

DEPENDS = " \
    appstream \
    bison-native \
    curl \
    dconf \
    fuse3 \
    gdk-pixbuf \
    glib-2.0 \
    gpgme \
    json-glib \
    libarchive \
    libcap \
    libxml2 \
    libxslt-native \
    ostree \
    polkit \
    python3-pyparsing-native \
    xmlto-native \
    zstd \
"

RDEPENDS:${PN} = " \
    bubblewrap \
    ca-certificates \
    dconf \
    flatpak-xdg-utils \
    xdg-dbus-proxy \
"

EXTRA_OEMESON += "-Dsystem_dbus_proxy=${bindir}/xdg-dbus-proxy -Dsystem_bubblewrap=${bindir}/bwrap"

GIR_MESON_OPTION = ""

PACKAGECONFIG[tests] = "-Dtests=true,-Dtests=false,xauth socat-native"
PACKAGECONFIG[xauth] = "-Dxauth=enabled,-Dxauth=disabled,xauth"
PACKAGECONFIG[seccomp] = "-Dseccomp=enabled,-Dseccomp=disabled,libseccomp"

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xauth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'seccomp', 'seccomp', '', d)} \
"

FILES:${PN} += "${libdir} ${datadir}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --user-group --home-dir ${sysconfdir}/polkit-1 polkitd"

do_install:append() {
    chmod 0700 ${D}/${datadir}/polkit-1/rules.d
    chown polkitd ${D}/${datadir}/polkit-1/rules.d
    chgrp root ${D}/${datadir}/polkit-1/rules.d
}
