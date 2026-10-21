SUMMARY = "a little task queue for python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5cac039fcc82f01141cc170b48f315d4"


SRC_URI[sha256sum] = "ac9b02e741f13da4092ea19bcaafc73669e97d3153914d2fed2868df144f6dfa"

RDEPENDS:${PN} += " \
	python3-datetime \
	python3-logging \
	python3-multiprocessing \
	python3-json \
"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

