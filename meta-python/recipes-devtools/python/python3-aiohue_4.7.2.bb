DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "8107f6fa9ad06eb91aef987a68bea7d3450510a433aed3a111e2827d37a37586"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-aiohttp \
    python3-asyncio-throttle \
    python3-awesomeversion \
"
