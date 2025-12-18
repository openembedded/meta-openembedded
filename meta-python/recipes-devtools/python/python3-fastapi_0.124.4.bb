DESCRIPTION = "FastAPI framework, high performance, easy to learn, fast to code, ready for production"
HOMEPAGE = "https://fastapi.tiangolo.com/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=95792ff3fe8e11aa49ceb247e66e4810"

SRC_URI[sha256sum] = "0e9422e8d6b797515f33f500309f6e1c98ee4e85563ba0f2debb282df6343763"

SRC_URI += "file://run-ptest"

inherit pypi python_pdm ptest-python-pytest

PACKAGECONFIG ?= ""
# swagger-ui is in meta-webserver that meta-python does not depend upon
# Users can still enable it in their distros explicitly but its disabled
# by default
PACKAGECONFIG[swagger-ui] = ",,,swagger-ui"

RDEPENDS:${PN} += "\
    python3-annotated-doc \
    python3-fastapi-cli \
    python3-json \
    python3-pydantic \
    python3-starlette \
    python3-typing-extensions \
"

RDEPENDS:${PN}-ptest += "\
                        python3-coverage \
                        python3-httpx \
                        python3-orjson \
                        python3-dirty-equals \
                        python3-pytest-httpx \
                        python3-python-multipart \
                        python3-sqlalchemy \
                        python3-trio \
"
