SUMMARY = "A minimal low-level HTTP client"
DESCRIPTION = "The HTTP core package of httpx2: connection pooling, \
    HTTP/1.1 and HTTP/2 transport and proxy support."
HOMEPAGE = "https://github.com/pydantic/httpx2"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=77c5a523c15d4a5887b081bba41ca110"

SRC_URI[sha256sum] = "6c519d0f7b44f7269fa9bbb4d2763db395a4a2370f046d8e8e0a4b38227815d2"

inherit pypi python_hatchling

DEPENDS += "\
    python3-uv-dynamic-versioning-native \
    python3-hatch-fancy-pypi-readme-native \
    python3-jinja2-native \
    python3-tomlkit-native \
    python3-dunamai-native \
"

# anyio comes from the "asyncio" extra, which httpx2 needs for its async
# client.
RDEPENDS:${PN} += "\
    python3-anyio \
    python3-h11 \
    python3-truststore \
"
