DESCRIPTION = "MiniDLNA (aka ReadyDLNA) is server software with the aim of \
being fully compliant with DLNA/UPnP-AV clients."
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b1a795ac1a06805cf8fd74920bc46b5c"

DEPENDS = "flac libav jpeg sqlite3 libexif libogg libid3tag"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}_${PV}_src.tar.gz \
    file://search-for-headers-in-staging-dir.patch \
    file://fix-makefile.patch \
"

SRC_URI[md5sum] = "d966256baf2f9b068b9de871ab5dade5"
SRC_URI[sha256sum] = "170560fbe042c2bbcba78c5f15b54f4fac321ff770490b23b55789be463f2851"

export STAGING_DIR_HOST

inherit autotools

