DESCRIPTION = "pytest-asyncio is an Apache2 licensed library, written in Python, for testing asyncio code with pytest"
HOMEPAGE = "https://github.com/pytest-dev/pytest-asyncio"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=136e4f49dbf29942c572a3a8f6e88a77"

SRC_URI[sha256sum] = "796aa822981e01b68c12e4827b8697108f7205020f24b5793b3c41555dab68ea"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "pytest_asyncio"

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-pytest \
"

BBCLASSEXTEND = "native nativesdk"
