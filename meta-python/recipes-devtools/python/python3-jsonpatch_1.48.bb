SUMMARY  = "Appling JSON patches in Python 2.6+ and 3.x"
HOMEPAGE = "https://github.com/stefankoegl/python-json-patch"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4f81c84f9a053e31fe9402a2a4e78864"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "81e9a03754137a1201d9b534ddd599cc0eb5983fe941bce238dc8a76e442bb94"

RDEPENDS:${PN} += " \
    python3-json \
    python3-jsonpointer \
    python3-netclient \
    python3-stringold \
"
