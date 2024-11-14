SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=e77986dc8e2ee22d44a7c863e96852ae"

SRC_URI[sha256sum] = "7a82c7bd0327266ef9f04a5272b0bb79812ddcefccf944320b5f3519586bbc82"

inherit pypi python_poetry_core cython

RDEPENDS:${PN} += " \
    python3-ifaddr (>=0.1.7) \
    python3-async-timeout \
"
