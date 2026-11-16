SUMMARY = "REPL plugin for Click"
HOMEPAGE = "https://github.com/untitaker/click-repl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=580facc4832cac548fad94845542da44"

SRC_URI[sha256sum] = "c32a1cf6f95e5bd6e92076f81ce24eafd33f2f0ffb0135887e335b8e446d1c0b"

inherit pypi python_setuptools_build_meta


DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += " \
	python3-click \
	python3-prompt-toolkit \
	python3-typing-extensions \
	"
