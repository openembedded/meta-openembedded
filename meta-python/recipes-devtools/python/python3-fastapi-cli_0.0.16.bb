DESCRIPTION = "Run and manage FastAPI apps from the command line with FastAPI CLI"
HOMEPAGE = "https://fastapi.tiangolo.com/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e0cf8d811ea2046529403707b266fb5a"

SRC_URI[sha256sum] = "e8a2a1ecf7a4e062e3b2eec63ae34387d1e142d4849181d936b23c4bdfe29073"

inherit pypi python_pdm

PYPI_PACKAGE = "fastapi_cli"

UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

RDEPENDS:${PN} += "\
    python3-difflib \
    python3-pydantic \
    python3-rich-toolkit \
    python3-typer \
    python3-uvicorn \
"
