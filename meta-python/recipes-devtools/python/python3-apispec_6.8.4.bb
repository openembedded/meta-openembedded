SUMMARY = "A pluggable API specification generator. Currently supports the OpenAPI Specification (f.k.a. the Swagger specification)."
HOMEPAGE = "https://github.com/marshmallow-code/apispec"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a75956865b40c80a37c1e864716592b4"

inherit pypi python_flit_core

SRC_URI[sha256sum] = "fd0ed14af71a2949d9aeb96b72ce1517cb4a48a5c55667cd0483b7ed33156a2a"

RDEPENDS:${PN} += "python3-packaging"
