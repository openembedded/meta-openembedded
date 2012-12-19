require gupnp.inc

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.19/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "8165c3ad3d16576a0a99250b75df23f8"
SRC_URI[sha256sum] = "0d3097c419e1f8af0ff8ee91318d75e8896b3b92924cb1a5cf93010795a7118b"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp/gupnp.h;beginline=1;endline=20;md5=d78a69d9b6e63ee2dc72e7b674d97520"

DEFAULT_PREFERENCE = "-1"
