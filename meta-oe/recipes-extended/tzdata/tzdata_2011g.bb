require tzdata.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://asia;beginline=2;endline=3;md5=06468c0e84ef4d4c97045a4a29b08234"

# Note that elsie.nci.nih.gov removes old archives after a new one is
# released. So if this doesn't build for you because of missing source file
# just bump it to the latest available version, removing the old one

PR = "${INC_PR}.0"
SRC_URI[md5sum] = "a068c27e7e426fdb12ab0c88506df20d"
SRC_URI[sha256sum] = "01a2a189eeda63baacc5e68e6c13afffc7edce182102fffc53acbf35e8703d3c"
