DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "d592a9055b56462312fcfccf46601ed4092698db1be5f3fc30c232ea34c6bbe3"

inherit pypi setuptools3

RDEPENDS_${PN} += "${PYTHON_PN}-aiohttp"
