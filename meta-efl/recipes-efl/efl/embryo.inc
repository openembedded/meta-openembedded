DESCRIPTION = "The Enlightenment C-like scripting language for Edje"

inherit efl

LICENSE = "MIT BSD CompuPhase"
LIC_FILES_CHKSUM = "file://COPYING;md5=220a7f1107df42c62428d8ebe559ed14"

BBCLASSEXTEND = "native"
INC_PR = "r0"

DEPENDS += "eina"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""
