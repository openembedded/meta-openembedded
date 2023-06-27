SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=6517bdc8f2416f27ab725d4702f7aac3"

SRC_URI[sha256sum] = "c6520f39f718e6bd16a87e0a7711ff65acfbb11bbc9fabb6283506833e0c3c0f"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-ifaddr \
    ${PYTHON_PN}-asyncio \
    ${PYTHON_PN}-async-timeout \
"
