DESCRIPTION = "FarSight is an audio/video conferencing framework specifically designed for Instant Messengers."
HOMEPAGE = "http://farsight.sf.net"
SRC_URI = "http://farsight.freedesktop.org/releases/farsight2/${P}.tar.gz"
LICENSE = "unknown"

DEPENDS = "libnice glib-2.0 libxml2 zlib dbus gstreamer gst-plugins-base"

inherit autotools

EXTRA_OECONF = " \
  --disable-debug \
  --disable-gtk-doc \
  --disable-python \
"

FILES_${PN} += "${libdir}/*/*.so"
FILES_${PN}-dev += "${libdir}/*/*a"
FILES_${PN}-dbg += "${libdir}/*/.debug"


SRC_URI[md5sum] = "e1f540cf3ebab06c3d7db1f46b44ac88"
SRC_URI[sha256sum] = "3ae59aa61a8071c9fad111e5fd606aabc27961eb4192f8729987a27dae6b3974"

