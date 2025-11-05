SUMMARY = "Starlette is a lightweight ASGI framework/toolkit, which is ideal for building async web services in Python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=11e8c8dbfd5fa373c703de492140ff7a"

SRC_URI[sha256sum] = "481a43b71e24ed8c43b11ea02f5353d77840e01480881b8cb5a26b8cae64a8cb"

inherit pypi python_hatchling ptest

PYPI_PACKAGE = "starlette"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-attrs \
        python3-ctypes \
        python3-httpx \
        python3-itsdangerous \
        python3-jinja2 \
        python3-outcome \
        python3-pytest \
        python3-pytest-asyncio \
        python3-pytest-forked \
        python3-python-multipart \
        python3-sortedcontainers \
        python3-terminal \
        python3-trio \
        python3-typing-extensions \
        python3-unittest-automake-output \
        python3-pyyaml \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += " \
        python3-anyio \
"
