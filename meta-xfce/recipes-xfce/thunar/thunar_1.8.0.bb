SUMMARY = "File manager for the Xfce Desktop Environment"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo glib-2.0 gtk+3 gdk-pixbuf libxfce4util libxfce4ui xfconf libsm dbus-glib startup-notification libnotify xfce4-panel udev"

inherit xfce gobject-introspection distro_features_check

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/Thunar-${PV}.tar.bz2"
SRC_URI[md5sum] = "245ca8e9111c44667032023199ca7778"
SRC_URI[sha256sum] = "acf151bceedb20aed45a6da61d7bb85a4a03964090c80454e72e61e7fc9e49c7"

S = "${WORKDIR}/Thunar-${PV}/"

PACKAGECONFIG ??= ""
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre"

FILES_${PN} += " \
    ${libdir}/thunarx-3/* \
    ${libdir}/xfce4/panel/plugins/* \
    ${libdir}/Thunar/[Tt]hunar* \
    ${libdir}/systemd \
    ${datadir}/dbus-1 \
    ${datadir}/metainfo \
    ${datadir}/polkit-1 \
    ${datadir}/Thunar \
    ${datadir}/xfce4/panel/plugins/* \
"

RRECOMMENDS_${PN} = "gvfs gvfsd-trash"
