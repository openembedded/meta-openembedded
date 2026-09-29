SUMMARY = "ICMP ping in pure Python"
HOMEPAGE = "https://github.com/kyan001/ping3"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3ae28ad230db5766d33276b580887102"

DEPENDS += "python3-wheel-native"

SRC_URI[sha256sum] = "6c99bc844e0b7dbc5c9765e8b530140daf1ccd2112c99db01ab79831bd8081cd"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-io \
    python3-logging \
"
