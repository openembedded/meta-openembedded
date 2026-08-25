SUMMARY = "Glances is an open-source system cross-platform monitoring tool."
HOMEPAGE = "https://nicolargo.github.io/glances/"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=852ecadc0ac7e6f4d7144d5544a3815b"

SRC_URI[sha256sum] = "736faa7faea3bcd58afffd6443974b4ed4b498627a71ce5d6c9640b7b5ddbd94"

PYPI_PACKAGE = "glances"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI += " \
	file://run-ptest \
"

# psutil/jinja2/packaging are oe-core; fastapi/uvicorn/defusedxml are
# meta-python.
RDEPENDS:${PN} += " \
    python3-psutil \
    python3-packaging \
    python3-defusedxml \
    python3-fastapi \
    python3-uvicorn \
    python3-jinja2 \
    python3-json \
    python3-logging \
    python3-asyncio \
    python3-multiprocessing \
    python3-netclient \
    python3-xml \
"

RDEPENDS:${PN}-ptest += " \
    python3-requests \
"

# Optional fast-JSON accelerator glances uses if present.
RRECOMMENDS:${PN} += "python3-orjson"
