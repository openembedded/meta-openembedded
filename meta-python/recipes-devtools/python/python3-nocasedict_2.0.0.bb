SUMMARY = "A case-insensitive ordered dictionary for Python"
HOMEPAGE = "https://github.com/pywbem/nocasedict"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1803fa9c2c3ce8cb06b4861d75310742"

SRC_URI[sha256sum] = "33bf7b0ea50eee6bad16dc7400fd89dd2d5379d9ba9cf17634bf2a59ae36ff0a"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-six \
"
