SUMMARY = "A Python implementation of the CANopen standard"
HOMEPAGE = "https://github.com/canopen-python/canopen"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=97f135a6ee6f800c377b5512122c7a8d"

SRC_URI[sha256sum] = "20a84bc498b34dadd79cece467d3bbe19591c1c02a8f39331bcc6065c4d8b2eb"

inherit setuptools3 pypi

RDEPENDS:${PN} += "\
  python3-can \
"

PACKAGECONFIG ?= ""
PACKAGECONFIG[canmatrix] = ",,,python3-canmatrix"
