SUMMARY = "Ordered YAML loader and dumper for PyYAML."
HOMEPAGE = "https://github.com/Phynix/yamlloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6831ef36faa29329bce2420c5356f97e"

SRC_URI[sha256sum] = "35669fd7b9f8c6b38db861a51701542c42672b46e8ab63253486a8cb8377b687"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN}:class-target += "\
    ${PYTHON_PN}-pyyaml \
"
