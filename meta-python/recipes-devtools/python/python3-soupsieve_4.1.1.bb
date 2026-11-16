SUMMARY = "CSS selector library for python-beautifulsoup4"
HOMEPAGE = "https://github.com/facelessuser/soupsieve"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=74851a2f1e5c07496dcb452af6a6bf54"

SRC_URI[sha256sum] = "b296bd2d2f65b1a78149f3d7bf6736a147d2c9e2dfbde0e4404639d748a02e03"

inherit pypi python_hatchling ptest-python-pytest

RDEPENDS:${PN} += " \
    python3-core \
    python3-datetime \
"
RDEPENDS:${PN}:append:class-target = " \
    python3-beautifulsoup4 \
"

RDEPENDS:${PN}-ptest += " \
    python3-beautifulsoup4 \
    python3-typing-extensions \
"

BBCLASSEXTEND = "native nativesdk"
