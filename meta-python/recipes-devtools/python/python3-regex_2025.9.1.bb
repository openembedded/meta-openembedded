SUMMARY = "Alternative regular expression module, to replace re."
HOMEPAGE = "https://bitbucket.org/mrabarnett/mrab-regex/src"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7b5751ddd6b643203c31ff873051d069"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "88ac07b38d20b54d79e704e38aa3bd2c0f8027432164226bdee201a1c0c9c9ff"

RDEPENDS:${PN} += " \
	python3-stringold \
"

BBCLASSEXTEND = "native nativesdk"
