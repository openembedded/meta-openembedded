SUMMARY = "Filesystem events monitoring"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "d7dc3bf0c3417c002ee976e958479c54d8ca18cbb3532fadbe69511fa389da15"

inherit pypi setuptools3

PACKAGECONFIG ??= "watchmedo"
PACKAGECONFIG[watchmedo] = ",,,python3-pyyaml"

BBCLASSEXTEND = "native nativesdk"
