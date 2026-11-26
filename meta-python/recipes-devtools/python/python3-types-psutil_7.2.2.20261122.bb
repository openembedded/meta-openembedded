SUMMARY = "Typing stubs for psutil"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ec038232ab86edd7354b091c54e190e2"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "30879c88d871c99f4a632cb17826ce8ec55de2c4884ed84e4f8f293b91c6439e"

BBCLASSEXTEND = "native"

RDEPENDS:${PN} += "\
	python3-psutil \
"
