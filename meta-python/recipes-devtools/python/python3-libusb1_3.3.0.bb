SUMMARY = "Pure-python wrapper for libusb-1.0"
HOMEPAGE = "http://github.com/vpelletier/python-libusb1"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
    file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c \
"

SRC_URI[sha256sum] = "b642aa518689f8e053f61955cba274e72bc8d0794c65d7990745aaae90dfc8a1"

RDEPENDS:${PN} = "libusb1"

inherit setuptools3 pypi
