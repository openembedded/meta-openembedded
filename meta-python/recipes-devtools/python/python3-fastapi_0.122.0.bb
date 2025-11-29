DESCRIPTION = "FastAPI framework, high performance, easy to learn, fast to code, ready for production"
HOMEPAGE = "https://fastapi.tiangolo.com/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=95792ff3fe8e11aa49ceb247e66e4810"

SRC_URI[sha256sum] = "cd9b5352031f93773228af8b4c443eedc2ac2aa74b27780387b853c3726fb94b"

SRC_URI += "file://run-ptest"

inherit pypi python_pdm ptest-python-pytest

RDEPENDS:${PN} += "\
    python3-annotated-doc \
    python3-fastapi-cli \
    python3-json \
    python3-pydantic \
    python3-starlette \
    python3-typing-extensions \
"

RRECOMMENDS:${PN} += "swagger-ui"

RDEPENDS:${PN}-ptest += "python3-orjson"
