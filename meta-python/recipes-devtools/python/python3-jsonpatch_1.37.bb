SUMMARY  = "Appling JSON patches in Python 2.6+ and 3.x"
HOMEPAGE = "https://github.com/stefankoegl/python-json-patch"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4f81c84f9a053e31fe9402a2a4e78864"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "04288ef93e60084bcb118101cd9d53424e627b91bb47ec5a95c02ec3795f6d23"

RDEPENDS:${PN} += " \
    python3-json \
    python3-jsonpointer \
    python3-netclient \
    python3-stringold \
"
