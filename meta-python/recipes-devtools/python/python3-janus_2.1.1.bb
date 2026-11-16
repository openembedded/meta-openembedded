SUMMARY = "Mixed sync-async queue to interoperate between asyncio tasks and classic threads"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=23878c357ebb4c8ce1109be365043349"

SRC_URI += "file://0001-pyproject.toml-Relax-the-uv_build-version-bound.patch"
SRC_URI[sha256sum] = "da6197870a8c634fed91698b5fd9725c33b83cb693baabf264320d7773e53659"

inherit pypi python_uv_build

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += "\
    python3-asyncio \
    python3-threading \
"

BBCLASSEXTEND = "native nativesdk"
