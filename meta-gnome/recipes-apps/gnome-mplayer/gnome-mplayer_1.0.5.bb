DESCRIPTION = "A GTK+ interface to MPlayer"
HOMEPAGE = "http://code.google.com/p/gnome-mplayer"
SECTION = "multimedia"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gmtk gtk+ alsa-lib libnotify glib-2.0 dbus-glib virtual/libx11 libxscrnsaver"

SRC_URI = "http://${PN}.googlecode.com/files/${P}.tar.gz"
SRC_URI[md5sum] = "1d3ab24c5501b5528e357931ca4dc6da"
SRC_URI[sha256sum] = "ac3c179345baecb4ca5237782aa33e83253a87bf8b42ce6eb3a9207a340f61b2"

EXTRA_OECONF = "--with-gio --with-alsa --with-dbus --with-libnotify"

FILES_${PN} += "${datadir}/gnome-control-center/default-apps/${PN}.xml"

inherit gettext pkgconfig mime gtk-icon-cache autotools

RDEPENDS_${PN} = "mplayer"
