SUMMARY = "File identification library for Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbdc006359f3157660173ec7f133a80e"

PYPI_PACKAGE = "identify"

inherit pypi setuptools3
SRC_URI[sha256sum] = "e4f4864b96c6557ef2a1e1c951771838f4edc9df3a72ec7118b338801b11c7bf"

RDEPENDS:${PN} = " \
	python3-ukkonen \
"
