SUMMARY = "Simplified object serialization in python"
DESCRIPTION = "Marshmallow is an ORM/ODM/framework-agnostic library for converting complex datatypes, such as objects, to and from native Python datatypes."
HOMEPAGE = "https://github.com/marshmallow-code/marshmallow"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
                    file://LICENSE;md5=27586b20700d7544c06933afe56f7df4 \
                    file://docs/license.rst;md5=13da439ad060419fb7cf364523017cfb"

SRC_URI[sha256sum] = "908acabd5aa14741419d3678d3296bda6abe28a167b7dcd05969ceb8256943ac"

CVE_PRODUCT = "marshmallow"

inherit python_flit_core pypi ptest-python-pytest

RDEPENDS:${PN}-ptest += " \
        python3-pytz \
        python3-simplejson \
        python3-zoneinfo \
        python3-tzdata \
"

do_install_ptest:append () {
        rm -rf ${D}${PTEST_PATH}/tests/mypy_test_cases
}

RDEPENDS:${PN} += " \
        python3-compression \
        python3-datetime \
        python3-email \
        python3-json \
        python3-numbers \
        python3-pprint \
        python3-packaging \
"
