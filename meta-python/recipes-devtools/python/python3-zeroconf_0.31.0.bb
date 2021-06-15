SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=27818cd7fd83877a8e3ef82b82798ef4"

SRC_URI[sha256sum] = "53a180248471c6f81bd1fffcbce03ed93d7d8eaf10905c9121ac1ea996d19844"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-ifaddr \
"
