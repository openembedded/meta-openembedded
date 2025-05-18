SUMMARY = "A next generation HTTP client for Python."
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=c624803bdf6fc1c4ce39f5ae11d7bd05"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "a0cb88a46f32dc874e04ee956e4c2764aba2aa228f650b06788ba6bda2962ab5"

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
    python3-json \
    python3-core \
    python3-netclient \
    python3-compression \
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
    ${libdir}/${PYTHON_DIR} \
"

FILES:${PN}-cli = "\
    ${bindir}/httpx \
"
