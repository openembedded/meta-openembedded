SUMMARY = "Python bindings for interacting with systemd over DBus"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI[sha256sum] = "f217b5792f1e7c391ae46262f43566b6f83364507eaab5d5e73fcd05ab12d2bb"

DEPENDS = "systemd"
RDEPENDS:${PN} += "\
    python3-xml \
    python3-lxml \
    python3-pprint \
    python3-core \
    python3-shell \
    python3-io \
"
REQUIRED_DISTRO_FEATURES = "systemd"

inherit pypi python_setuptools_build_meta features_check pkgconfig cython

do_configure:prepend() {
    rm -rf ${S}/pystemd/*.c
}

BBCLASSEXTEND = "native"
