SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=6517bdc8f2416f27ab725d4702f7aac3"

SRC_URI[sha256sum] = "9424551ef5a8d91fa8296bb1791e83b31afcd17a336a071928d4a978d7b96b72"

DEPENDS += "python3-cython-native"

inherit pypi python_poetry_core

RDEPENDS:${PN} += " \
    python3-ifaddr (>=0.1.7) \
    python3-async-timeout \
"
