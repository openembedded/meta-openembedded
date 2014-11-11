require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "A program to compile XKB keyboard description"
DESCRIPTION = "The  xkbevd event daemon listens for specified XKB \
events and executes requested commands if they occur. "

LIC_FILES_CHKSUM = "file://COPYING;md5=208668fa9004709ba22c2b748140956c"

DEPENDS += "libxkbfile"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "37ed71525c63a9acd42e7cde211dcc5b"
SRC_URI[sha256sum] = "55b2484f9851112c2685d503b64b319f0f98fce74863ef735ecd52b52ceb0116"
