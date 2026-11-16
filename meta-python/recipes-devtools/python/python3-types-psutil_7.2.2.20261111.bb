SUMMARY = "Typing stubs for psutil"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ec038232ab86edd7354b091c54e190e2"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "d677119c44fb80cc9be41d8ac731f08bf89974bb1daa49b6688665a9d7224f42"

BBCLASSEXTEND = "native"

RDEPENDS:${PN} += "\
	python3-psutil \
"
