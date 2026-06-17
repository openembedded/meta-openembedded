SUMMARY = "Python library for CycloneDX"
HOMEPAGE = "https://github.com/CycloneDX/cyclonedx-python-lib/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "d03d6ea271e26feaf123b8b1b34468a305f33a338c5763f56e397a8408f9b290"

inherit pypi python_poetry_core

PYPI_PACKAGE = "cyclonedx_python_lib"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} = "\
    python3-py-serializable \
    python3-sortedcontainers \
    python3-jsonschema \
    python3-referencing \
"
