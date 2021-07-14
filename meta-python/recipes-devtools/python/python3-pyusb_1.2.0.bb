SUMMARY = "PyUSB provides USB access on the Python language"
HOMEPAGE = "http://pyusb.sourceforge.net/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e64a29fcd3c3dd356a24e235dfcb3905"

DEPENDS += "libusb1 ${PYTHON_PN}-setuptools-scm-native"

RDEPENDS_${PN} += " \
	python3-logging \
"

SRC_URI[sha256sum] = "d68597d2cf7df766bdf816b1a337b72ab8233c19825e170ae18714f16b838cbc"

inherit pypi setuptools3

RDEPENDS_${PN} += "libusb1"

BBCLASSEXTEND = "native nativesdk"
