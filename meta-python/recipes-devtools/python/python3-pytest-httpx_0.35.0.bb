SUMMARY = "Send responses to httpx."
HOMEPAGE = "https://github.com/Colin-b/pytest_httpx"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=80d06bde9296c367ea063b11e9518842"
RECIPE_MAINTAINER = "Tom Geelen <t.f.g.geelen@gmail.com>"

SRC_URI[sha256sum] = "d619ad5d2e67734abfbb224c3d9025d64795d4b8711116b1a13f72a251ae511f"

inherit pypi python_setuptools_build_meta ptest-python-pytest

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += "\
    python3-httpx (>=0.28) \
    python3-pytest (>=8) \
"

RDEPENDS:${PN}-ptest += "\
    python3-pytest-cov \
    python3-pytest-asyncio \
"

PYPI_PACKAGE = "pytest_httpx"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"
