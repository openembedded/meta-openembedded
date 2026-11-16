SUMMARY = "Filesystem events monitoring"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "d3861e255416f49a1384474cb2efa097de165fb330d4c5432ce6708b509ed922"

inherit pypi setuptools3

PACKAGECONFIG ??= "watchmedo"
PACKAGECONFIG[watchmedo] = ",,,python3-pyyaml"

BBCLASSEXTEND = "native nativesdk"
