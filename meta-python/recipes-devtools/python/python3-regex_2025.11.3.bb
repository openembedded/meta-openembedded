SUMMARY = "Alternative regular expression module, to replace re."
HOMEPAGE = "https://bitbucket.org/mrabarnett/mrab-regex/src"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7b5751ddd6b643203c31ff873051d069"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "1fedc720f9bb2494ce31a58a1631f9c82df6a09b49c19517ea5cc280b4541e01"

RDEPENDS:${PN} += " \
	python3-stringold \
"

BBCLASSEXTEND = "native nativesdk"
