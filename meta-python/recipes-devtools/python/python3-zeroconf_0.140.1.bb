SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=93e84222c4bfa3e70c20bb48ae9893b1"

SRC_URI[sha256sum] = "477d396cb712247aa5859e59249aabdad3fab1139097794e70b2e4b1625047bf"

inherit pypi python_poetry_core cython

RDEPENDS:${PN} += " \
    python3-ifaddr (>=0.1.7) \
    python3-async-timeout \
"
