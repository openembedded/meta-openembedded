SUMMARY = "a little task queue for python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5cac039fcc82f01141cc170b48f315d4"

PYPI_PACKAGE = "huey"

SRC_URI[sha256sum] = "a39010628a9a1a9e91462f9bf33dc243b006a9f21193026ea47ae18949a12581"

RDEPENDS:${PN} += " \
	python3-datetime \
	python3-logging \
	python3-multiprocessing \
	python3-json \
"

inherit pypi setuptools3

