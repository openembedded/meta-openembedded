require recipes-graphics/pango/pango.inc

BBCLASSEXTEND = "native"
DEPENDS_virtclass-native = "glib-2.0-native cairo-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

SRC_URI[archive.md5sum] = "a5ee785f4f31d6bdd8625a09ea3f8b4b"
SRC_URI[archive.sha256sum] = "f15deecaecf1e9dcb7db0e4947d12b5bcff112586434f8d30a5afd750747ff2b"

SRC_URI += "file://no-tests.patch"

PR = "r4"

EXTRA_OECONF += "--disable-introspection"
