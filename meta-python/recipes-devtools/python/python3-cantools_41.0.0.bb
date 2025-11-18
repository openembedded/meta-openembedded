DESCRIPTION = "CAN BUS tools in Python 3."
HOMEPAGE = "https://github.com/eerimoq/cantools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9aa4ec07de78abae21c490c9ffe61bd"

SRC_URI[sha256sum] = "81d7efa32d66a149934b21b691a40fb40db9e0b74fa02c24f4f257f587d169ae"

PYPI_PACKAGE = "cantools"

inherit pypi python_poetry_core

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += "\
    python3-can \
    python3-bitstruct \
    python3-core \
    python3-textparser \
    python3-typing-extensions \
    python3-diskcache \
    python3-asyncio \
"

CLEANBROKEN = "1"
