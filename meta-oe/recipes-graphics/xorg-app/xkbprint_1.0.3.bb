require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "A program to compile XKB keyboard description"
DESCRIPTION = "xkbprint generates a printable or encapsulated PostScript \
description of an XKB keyboard description."

LIC_FILES_CHKSUM = "file://COPYING;md5=20f28f97555b220fde762bc2a4406a8f"

DEPENDS += "libxkbfile"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "526b5ba87cccd05aa700fa628bcfda3e"
SRC_URI[sha256sum] = "fa846db8e588018134bd550f80eb129e4ddfdb96f3b157603a57ae2cdf5892c0"
