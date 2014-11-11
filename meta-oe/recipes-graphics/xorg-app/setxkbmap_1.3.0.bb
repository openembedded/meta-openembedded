require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "A program to compile XKB keyboard description"

DESCRIPTION = "The xkbcomp keymap compiler converts a description of an \
XKB keymap into one of several output formats. The most common use for \
xkbcomp is to create a compiled keymap file (.xkm extension) which can \
be read directly by XKB-capable X servers or utilities."

LIC_FILES_CHKSUM = "file://COPYING;md5=5feafdbe6dfe9e2bd32325be0cfc86f8"

PE = "1"

DEPENDS += "libxkbfile"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "1001771344608e120e943a396317c33a"
SRC_URI[sha256sum] = "3d305c9d1f38224169b2e501ef0665cc3ab52d4dbc2b9a60c9dbb047f77ddec6"
