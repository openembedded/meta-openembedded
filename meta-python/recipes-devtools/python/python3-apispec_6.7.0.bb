SUMMARY = "A pluggable API specification generator. Currently supports the OpenAPI Specification (f.k.a. the Swagger specification)."
HOMEPAGE = "https://github.com/marshmallow-code/apispec"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a75956865b40c80a37c1e864716592b4"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "fa2cf1063bb227ed6f7f66b019f320d7dc0a7cb02acdf166a160e2381f4b2c39"

RDEPENDS:${PN} += "python3-packaging"
