DESCRIPTION = "Asynchronous library to control Philips Hue"
HOMEPAGE = "https://pypi.org/project/aiohue/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab31a1d28183826937f4b152143a33f"

SRC_URI[sha256sum] = "77d7ee467c41bb0c3e00f4e2e533ba65f58a29147ed7df407f84cd4595d2d20c"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-aiohttp \
    python3-asyncio-throttle \
    python3-awesomeversion \
"
