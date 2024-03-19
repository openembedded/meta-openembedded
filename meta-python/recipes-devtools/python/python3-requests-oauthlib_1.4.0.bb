LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22d117a849df10d047ed9b792838e863"

SRC_URI[sha256sum] = "acee623221e4a39abcbb919312c8ff04bd44e7e417087fb4bd5e2a2f53d5e79a"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-requests python3-oauthlib"
