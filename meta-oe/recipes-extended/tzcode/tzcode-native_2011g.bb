require tzcode-native.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=3ae8198f82258417ce29066d3b034035"

# Note that elsie.nci.nih.gov removes old versions when new is coming out
# So if this doesn't build for you because of missing source file, just
# bump it to the latest available version, removing old one
# Also, tzdata (and it is needed to build tzcode) version can differ from
# tzcode version, thus this variable

TZDATA_PV = "${PV}"

PR = "${INC_PR}.0"

SRC_URI[tzcode-2011g.md5sum] = "ecb564279b28c5b184421c525d997d6c"
SRC_URI[tzcode-2011g.sha256sum] = "636c735d8df1276cc8ab88bc31bb36a21f91ed34e26d181303ecd8fe48021bc2"
SRC_URI[tzdata-2011g.md5sum] = "a068c27e7e426fdb12ab0c88506df20d"
SRC_URI[tzdata-2011g.sha256sum] = "01a2a189eeda63baacc5e68e6c13afffc7edce182102fffc53acbf35e8703d3c"

