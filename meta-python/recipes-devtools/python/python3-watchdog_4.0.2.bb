SUMMARY = "Filesystem events monitoring"
DEPENDS = "python3-argh"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "b4dfbb6c49221be4535623ea4474a4d6ee0a9cef4a80b20c28db4d858b64e270"

inherit pypi setuptools3

RDEPENDS:${PN} = " \
    python3-argh \
    python3-pathtools3 \
    python3-pyyaml \
    python3-requests \
"

BBCLASSEXTEND = "native nativesdk"
