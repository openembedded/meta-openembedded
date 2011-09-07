require tzcode-native.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=3ae8198f82258417ce29066d3b034035"

# Note that elsie.nci.nih.gov removes old versions when new is coming out
# So if this doesn't build for you because of missing source file, just
# bump it to the latest available version, removing old one
# Also, tzdata (and it is needed to build tzcode) version can differ from
# tzcode version, thus this variable

TZDATA_PV = "2011i"

PR = "${INC_PR}.2"

SRC_URI[tzcode-2011i.md5sum] = "cf7f4335b7c8682899fa2814e711c1b2"
SRC_URI[tzcode-2011i.sha256sum] = "f0dd991de3f8d6c599c104e294377c9befa1ef40aa5a1d09e2e295a453f3c1ec"
SRC_URI[tzdata-2011i.md5sum] = "c7a86ec34f30f8d6aa77ef94902a3047"
SRC_URI[tzdata-2011i.sha256sum] = "f8dde7ca5e61f21ac34c8cdbef5568d00c829981211098f059d8104964c81ffa"

