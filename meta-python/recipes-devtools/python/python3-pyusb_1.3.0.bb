SUMMARY = "PyUSB provides USB access on the Python language"
HOMEPAGE = "http://pyusb.sourceforge.net/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e64a29fcd3c3dd356a24e235dfcb3905"

DEPENDS += "libusb1 python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
	python3-logging \
"

SRC_URI[sha256sum] = "7e6de8ef79e164ced020d8131cd17d45a3cdeefb7afdaf41d7a2cbf2378828c3"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-ctypes"

BBCLASSEXTEND = "native nativesdk"
