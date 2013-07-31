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

SRC_URI[md5sum] = "8fee23d51be07c72f9a7edf707e1f43f"
SRC_URI[sha256sum] = "fe8af458b2f3e5e561181c7e86b763e2623397a2a4bbbfe08e58be06ed052306"
