DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "d36fa57747191b92e77c89c453c792f13b9af8f7ca9610bb4bb46f2636a536f6"

inherit pypi setuptools3

RDEPENDS_${PN} += "${PYTHON_PN}-aiohttp"
