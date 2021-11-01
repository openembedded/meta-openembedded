SUMMARY = "GNOME wallpapers"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

SECTION = "x11/gnome"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gettext allarch

SRC_URI[archive.sha256sum] = "1da1ac0d261bedf0fcd2c85b480bc65505e23cf51f1143126c0d37717e693145"

FILES:${PN} += " \
    ${datadir}/backgrounds \
    ${datadir}/gnome-background-properties \
"
