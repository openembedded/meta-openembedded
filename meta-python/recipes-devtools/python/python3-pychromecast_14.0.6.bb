SUMMARY = "Library for Python 3.6+ to communicate with the Google Chromecast."
HOMEPAGE = "https://github.com/balloob/pychromecast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1dbd4e85f47b389bdadee9c694669f5"

#SRC_URI += "file://0001-Allow-newer-version-of-wheel-and-setuptools.patch"
SRC_URI[sha256sum] = "8af91af4fb62d9d6e4df1832c6503dda74afb14c7726ede1ff863e178deb3f9a"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-zeroconf (>=0.131.0) \
    python3-protobuf (>=4.25.2) \
    python3-casttube (>=0.2.1) \
"
