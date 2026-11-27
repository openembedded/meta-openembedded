SUMMARY = "Pure-Python Reed Solomon encoder/decoder"
HOMEPAGE = "https://github.com/tomerfiliba/reedsolomon"
LICENSE = "MIT-0 OR Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ffde61aeb8917e70e0257e0a4b6d103c"

SRC_URI[sha256sum] = "5659d59208af96fe5abf07e72b49c17a1788d4d2962292268a3080ddb39085c4"

inherit pypi python_setuptools_build_meta cython

UPSTREAM_CHECK_REGEX = "(?i)reedsolo-(?P<pver>\d+(\.\d+)+(b\d+)?)\.tar\.gz"

RDEPENDS:${PN} += "python3-core"
