SUMMARY = "Helpers for AV applications using DLNA"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://libgupnp-dlna/gupnp-dlna-profile.h;beginline=1;endline=22;md5=1b85459f65cb1e73a885ca137aab6274"

DEPENDS = "libxml2 glib-2.0"

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.9/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "d972f9b03f0f3793cb895eb034ea314f"
SRC_URI[sha256sum] = "c28d5650604c7da4f193e6b85049a4885f3a01f1996df768d55b5cb898a9fc53"

inherit autotools pkgconfig

require no-vala.inc

# The GStreamer metadata backend requires GStreamer 1.0, so skip this until we
# have that packaged.
EXTRA_OECONF = "--disable-gstreamer-metadata-backend"

FILES_${PN} += "${datadir}/gupnp-dlna-2.0/dlna-profiles"
