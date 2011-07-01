require tzcode-native.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=3ae8198f82258417ce29066d3b034035"

# Note that elsie.nci.nih.gov removes old versions when new is coming out
# So if this doesn't build for you because of missing source file, just
# bump it to the latest available version, removing old one
# Also, tzdata (and it is needed to build tzcode) version can differ from
# tzcode version, thus this variable

TZDATA_PV = "2011h"

PR = "${INC_PR}.1"

SRC_URI[tzcode-2011g.md5sum] = "ecb564279b28c5b184421c525d997d6c"
SRC_URI[tzcode-2011g.sha256sum] = "636c735d8df1276cc8ab88bc31bb36a21f91ed34e26d181303ecd8fe48021bc2"
SRC_URI[tzdata-2011h.md5sum] = "546d27b6c1e5e1097bd512651815017f"
SRC_URI[tzdata-2011h.sha256sum] = "c098786d053736dd18d4f1fbebdf41554c6fd702a36f75c18f1c66712d2abc24"

