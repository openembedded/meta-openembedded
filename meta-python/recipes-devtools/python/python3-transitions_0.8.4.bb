inherit pypi setuptools3

SUMMARY = "A lightweight, object-oriented Python state machine implementation with many extensions."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de0a0876a688a4483bfafa764773ab39"

SRC_URI = "https://files.pythonhosted.org/packages/32/32/305845f95f98b505e641aa3fc135b0a9747befcff06223a541d2aa388d47/transitions-${PV}.tar.gz"
SRC_URI[md5sum] = "ef07ea90015a296653a4b095837b0354"
SRC_URI[sha256sum] = "9a2841b24789dfd345267cb92e26b79da75fd03f6021d1a5222c71b5c9ae3c16"

RDEPENDS_${PN} += "python3-six"
