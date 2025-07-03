SUMMARY = "Application to take screenshots"
HOMEPAGE = "https://docs.xfce.org/apps/xfce4-screenshooter/start"
SECTION = "x11/application"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "xfce4-panel libxfce4util libxfce4ui gdk-pixbuf gtk+3 glib-2.0 libsoup exo libxfixes xext virtual/libx11 libxml-parser-perl-native"

inherit xfce-app perlnative

SRC_URI[sha256sum] = "992066cfecfb44a68681340bfd55d524d40410aac3da6ef25c6c6cb2150a5965"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)}"
PACKAGECONFIG[wayland] = "--enable-wayland, --disable-wayland, wayland-native wayland"

EXTRA_OECONF += "WAYLAND_SCANNER=${STAGING_BINDIR_NATIVE}/wayland-scanner"

do_compile:prepend() {
    mkdir -p lib src panel-plugin protocols
}

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/xfce4/panel/plugins \
    ${libdir}/xfce4/panel/plugins \
"
