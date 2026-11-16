SUMMARY = "A libudev binding"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI[sha256sum] = "4e7faaec419b81a902d057568101819f448972c0cf448bb9c22203e4fc6a8eb9"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} = "\
    python3-ctypes \
    python3-misc \
    python3-threading \
    python3-fcntl \
    libudev \
"
