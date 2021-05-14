DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "59ac159301064d222192f7b58891d39a2f25aab620061ed12e7cfaf1b5d1a6cc"

inherit pypi setuptools3

RDEPENDS_${PN} += "${PYTHON_PN}-aiohttp"
