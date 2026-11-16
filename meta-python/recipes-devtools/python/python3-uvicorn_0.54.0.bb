SUMMARY = "The lightning-fast ASGI server."
HOMEPAGE = "https://github.com/encode/uvicorn"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=5c778842f66a649636561c423c0eec2e"
RECIPE_MAINTAINER = "Tom Geelen <t.f.g.geelen@gmail.com>"

SRC_URI[sha256sum] = "a2e33cbfaa0306f8e6b0c13e0cb89d7d7a2da3e62b90c66e18c33d9807b28620"

SRC_URI += "file://0001-ptest-disable-failing-tests.patch \
            file://0002-ptest-skip-test_headers.patch \
            "

inherit pypi python_hatchling ptest-python-pytest

CVE_PRODUCT = "encode:uvicorn"
CVE_STATUS[CVE-2020-7694] = "fixed-version: The vulnerability has been fixed since 0.11.7"

RDEPENDS:${PN} = "\
    python3-click \
    python3-h11 (>=0.8) \
    python3-httptools \
    python3-multiprocessing \
    python3-python-dotenv \
    python3-websockets \
    python3-wsproto \
"

RDEPENDS:${PN}-ptest += "\
    python3-a2wsgi \
    python3-httpx2 \
    python3-pytest-mock \
    python3-pyyaml \
"
