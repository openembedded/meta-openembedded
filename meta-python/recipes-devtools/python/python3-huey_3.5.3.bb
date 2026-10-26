SUMMARY = "a little task queue for python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5cac039fcc82f01141cc170b48f315d4"


SRC_URI[sha256sum] = "3d9e8fe95638bde843fbcfe38c063d906afa0d4b0cf94955a24c0f90eb09bb44"

RDEPENDS:${PN} += " \
	python3-datetime \
	python3-logging \
	python3-multiprocessing \
	python3-json \
"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

