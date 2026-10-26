DESCRIPTION = "pytest-asyncio is an Apache2 licensed library, written in Python, for testing asyncio code with pytest"
HOMEPAGE = "https://github.com/pytest-dev/pytest-asyncio"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=136e4f49dbf29942c572a3a8f6e88a77"

SRC_URI[sha256sum] = "e8266eb094e5ee9ffdb2068c9836f33e209ea77a970eb846933d416b5602669b"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-pytest \
"

BBCLASSEXTEND = "native nativesdk"
