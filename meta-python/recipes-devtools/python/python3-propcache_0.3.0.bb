SUMMARY = "Fast property caching"
HOMEPAGE = "https://github.com/aio-libs/propcache"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "a8fd93de4e1d278046345f49e2238cdb298589325849b2645d4a94c53faeffc5"

inherit pypi python_setuptools_build_meta ptest-python-pytest cython

DEPENDS += " \
	python3-expandvars-native \
"

RDEPENDS:${PN}-ptest += " \
	python3-pytest-codspeed \
	python3-pytest-xdist \
	python3-rich \
	python3-statistics \
"

