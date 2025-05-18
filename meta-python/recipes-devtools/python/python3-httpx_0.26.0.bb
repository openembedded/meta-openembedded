SUMMARY = "A next generation HTTP client for Python."
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=c624803bdf6fc1c4ce39f5ae11d7bd05"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "451b55c30d5185ea6b23c2c793abf9bb237d2a7dfb901ced6ff69ad37ec1dfaf"

DEPENDS += "\
    python3-hatch-fancy-pypi-readme-native \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[brotli] = ",,,python3-brotli"
PACKAGECONFIG[http2] = ",,,python3-h2"
PACKAGECONFIG[socks] = ",,,python3-socksio"

RDEPENDS:${PN} += "\
    python3-anyio \
    python3-certifi \
    python3-httpcore \
    python3-idna \
    python3-sniffio \
"

PACKAGES += "\
    ${PN}-cli \
"

RDEPENDS:${PN}-cli += "\
    ${PN} \
    python3-click \
    python3-pygments \
    python3-rich \
"

FILES:${PN} = "\
    /usr/lib/python${PYTHON_BASEVERSION} \
"

FILES:${PN}-cli = "\
    /usr/bin/httpx \
"
