SUMMARY = "Grilo is a framework forsearching media content from various sources"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = " \
    libxml2 \
    glib-2.0 \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gtk-doc gettext vala

SRC_URI[archive.sha256sum] = "884580e8c5ece280df23aa63ff5234b7d48988a404df7d6bfccd1e77b473bd96"

GIR_MESON_OPTION = "enable-introspection"
GTKDOC_MESON_OPTION = "enable-gtk-doc"

# Note: removing 'net' from PACKAGECONFIG causes
# | bindings/vala/meson.build:15:0: ERROR: Unknown variable "grlnet_gir".
PACKAGECONFIG ??= "net"

PACKAGECONFIG[net] = "-Denable-grl-net=true, -Denable-grl-net=false, libsoup-3.0"
PACKAGECONFIG[test-ui] = "-Denable-test-ui=true, -Denable-test-ui=false, gtk+3 liboauth"

# Once we have a recipe for 'totem-plparser' this can turn into a PACKAGECONFIG
EXTRA_OEMESON = "-Denable-grl-pls=false"

do_compile:append() {
        sed -i -e 's,${B}/../,,' ${B}/src/grl-type-builtins.h
}
