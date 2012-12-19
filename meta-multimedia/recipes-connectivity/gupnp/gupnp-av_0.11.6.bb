require gupnp-av.inc

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.11/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "ee200393207323f0b594f11d6184556c"
SRC_URI[sha256sum] = "401991336babb18c4ebed16e75e0b4d3e5848cff7bb878bbfd54a5d15203c4cc"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp-av/gupnp-av.h;beginline=1;endline=22;md5=2b47b7b5f799d2ebabe62b895e848820"

DEFAULT_PREFERENCE = "-1"
