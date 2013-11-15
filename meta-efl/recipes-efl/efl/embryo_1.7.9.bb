DESCRIPTION = "The Enlightenment C-like scripting language for Edje"

inherit efl

LICENSE = "MIT BSD CompuPhase"
LIC_FILES_CHKSUM = "file://COPYING;md5=220a7f1107df42c62428d8ebe559ed14"

BBCLASSEXTEND = "native"

DEPENDS += "eina"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "98614ec48376ff6d3a39187504b70ed4"
SRC_URI[sha256sum] = "6b2739a53c3ab2fcf44edfb9c3a4c56d11aba91e27e556a9e70116a161201e34"
