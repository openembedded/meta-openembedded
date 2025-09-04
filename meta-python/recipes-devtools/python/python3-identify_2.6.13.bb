SUMMARY = "File identification library for Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbdc006359f3157660173ec7f133a80e"

PYPI_PACKAGE = "identify"

inherit pypi setuptools3
SRC_URI[sha256sum] = "da8d6c828e773620e13bfa86ea601c5a5310ba4bcd65edf378198b56a1f9fb32"

RDEPENDS:${PN} = " \
	python3-ukkonen \
"
