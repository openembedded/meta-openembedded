SUMMARY = "File identification library for Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbdc006359f3157660173ec7f133a80e"

PYPI_PACKAGE = "identify"

inherit pypi setuptools3
SRC_URI[sha256sum] = "846857203b5511bbe94d5a352a48ef2359532bc8f6727b5544077a0dcfb24980"

RDEPENDS:${PN} = " \
	python3-ukkonen \
"
