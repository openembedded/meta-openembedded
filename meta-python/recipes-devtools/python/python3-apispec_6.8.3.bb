SUMMARY = "A pluggable API specification generator. Currently supports the OpenAPI Specification (f.k.a. the Swagger specification)."
HOMEPAGE = "https://github.com/marshmallow-code/apispec"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a75956865b40c80a37c1e864716592b4"

inherit pypi python_flit_core

SRC_URI[sha256sum] = "0823235aa171187fc0fb1dfd28211ebcf5fe7768b2a7e1929d06671e162439ae"

RDEPENDS:${PN} += "python3-packaging"
