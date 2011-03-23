DESCRIPTION = "Fribidi library for bidirectional text"
SECTION = "libs"
PRIORITY = "optional"
PR = "r1"
LICENSE = "unknown"

PROVIDES = "libfribidi"

# Slightly incompatible with 0.10.x, so:
DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_angstrom = "1"

inherit autotools lib_package pkgconfig

CFLAGS_append = "  -DPAGE_SIZE=4096 "

SRC_URI = "http://fribidi.org/download/fribidi-${PV}.tar.gz"

SRC_URI[md5sum] = "626db17d2d99b43615ad9d12500f568a"
SRC_URI[sha256sum] = "49cf91586e48b52fe25872ff66c1da0dff0daac2593f9f300e2af12f44f64177"
BBCLASSEXTEND = "native"
