require gssdp.inc

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.13/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "758ed423cdd258a9a22d59c321b535ed"
SRC_URI[sha256sum] = "8f663f8a72b66254f5c20a3610ff853ac8d00bf6f253828e37e51579caa34dce"

# This is a development release so don't prefer it
DEFAULT_PREFERENCE = "-1"
