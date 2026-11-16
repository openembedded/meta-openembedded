SUMMARY = "File manager for the Xfce Desktop Environment"
HOMEPAGE = "https://docs.xfce.org/xfce/thunar/start"
SECTION = "x11"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "libxml-parser-perl-native libxslt-native docbook-xsl-stylesheets-native exo gdk-pixbuf libxfce4ui libnotify xfce4-panel udev"

XFCE_COMPRESS_TYPE = "xz"
XFCEBASEBUILDCLASS = "meson"
GTKDOC_MESON_OPTION = "gtk-doc"

inherit xfce gobject-introspection mime-xdg perlnative gtk-doc

SRC_URI[sha256sum] = "fcb196b2f89cbcb8a3f5130fa9a1a11bc49d66a6403bc91aff7676648d95d51f"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"
PACKAGECONFIG[pcre] = "-Dpcre2=enabled,-Dpcre2=disabled,libpcre2"
PACKAGECONFIG[x11] = "-Dx11=enabled -Dsession-management=enabled,-Dx11=disabled,startup-notification virtual/libx11 xfce4-session"

FILES:${PN} += " \
    ${libdir}/thunarx-3/* \
    ${libdir}/xfce4/panel/plugins/* \
    ${libdir}/Thunar/[Tt]hunar* \
    ${systemd_user_unitdir} \
    ${datadir}/dbus-1 \
    ${datadir}/metainfo \
    ${datadir}/polkit-1 \
    ${datadir}/Thunar \
    ${datadir}/xfce4/panel/plugins/* \
"

RRECOMMENDS:${PN} = " \
    gvfs \
    gvfsd-trash \
    ${@bb.utils.contains('DISTRO_FEATURES', 'dbus', 'tumbler', '', d)} \
"
