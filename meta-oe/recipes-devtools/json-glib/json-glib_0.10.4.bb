DESCRIPTION = "JSON-GLib is a library providing serialization and deserialization support for the JavaScript Object Notation (JSON) format"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"
DEPENDS = "glib-2.0"

PR = "r1"

EXTRA_OECONF = "--enable-introspection=no"

inherit autotools
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/json-glib/0.10/json-glib-0.10.4.tar.gz"

SRC_URI[md5sum] = "87677e939a4b3d42e1c56b2a3046b9bb"
SRC_URI[sha256sum] = "9561b3c58b350c89333d154f1e8669b8ece611c9c724252aed0e6273cda9cc6f"
