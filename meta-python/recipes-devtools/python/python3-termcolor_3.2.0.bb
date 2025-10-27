SUMMARY = "ANSII Color formatting for output in terminal"
HOMEPAGE = "https://pypi.python.org/pypi/termcolor"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=e5f5f7c9b280511f124dba5dda3d180e"

inherit pypi python_hatchling

SRC_URI[sha256sum] = "610e6456feec42c4bcd28934a8c87a06c3fa28b01561d46aa09a9881b8622c58"

DEPENDS += " \
	python3-toml-native \
	python3-hatch-vcs-native \
"

BBCLASSEXTEND = "native"
