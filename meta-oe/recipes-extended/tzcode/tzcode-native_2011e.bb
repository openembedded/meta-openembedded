require tzcode-native.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=3ae8198f82258417ce29066d3b034035"

# Note that elsie.nci.nih.gov removes old versions when new is coming out
# So if this doesn't build for you because of missing source file, just
# bump it to the latest available version, removing old one
# Also, tzdata (and it is needed to build tzcode) version can differ from
# tzcode version, thus this variable

TZDATA_PV = "2011e"

PR = "${INC_PR}.0"

SRC_URI[tzcode-2011e.md5sum] = "fbfc05dbf9ebcfe7c4bba18549870173"
SRC_URI[tzcode-2011e.sha256sum] = "8fb00f8763aa51d83d6f3190d144124bb7176ca829fc08823d6205297bf0426b"
SRC_URI[tzdata-2011e.md5sum] = "044a07072300a0ee72b046e5a9a4ec90"
SRC_URI[tzdata-2011e.sha256sum] = "44fef01de4589a4979eb6b5fdbbfd21a3b135852af1ecbfb9e0368ae47392c79"
