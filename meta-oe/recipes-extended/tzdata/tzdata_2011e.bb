require tzdata.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://asia;beginline=2;endline=3;md5=06468c0e84ef4d4c97045a4a29b08234"

# Note that elsie.nci.nih.gov removes old archives after a new one is
# released. So if this doesn't build for you because of missing source file
# just bump it to the latest available version, removing the old one

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "044a07072300a0ee72b046e5a9a4ec90"
SRC_URI[sha256sum] = "44fef01de4589a4979eb6b5fdbbfd21a3b135852af1ecbfb9e0368ae47392c79"
