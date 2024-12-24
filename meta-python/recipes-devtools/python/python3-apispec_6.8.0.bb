SUMMARY = "A pluggable API specification generator. Currently supports the OpenAPI Specification (f.k.a. the Swagger specification)."
HOMEPAGE = "https://github.com/marshmallow-code/apispec"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a75956865b40c80a37c1e864716592b4"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "861cca82bbc0652ca9acea896921b254944580342fde849f86f6dac1acc6ca96"

RDEPENDS:${PN} += "python3-packaging"
