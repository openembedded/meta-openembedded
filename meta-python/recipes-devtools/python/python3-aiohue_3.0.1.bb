DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "f4933b8583d093da4c2772c40c59d262892539c7053996cea5b964a2cceaa386"

inherit pypi setuptools3

RDEPENDS:${PN} += "${PYTHON_PN}-aiohttp"
