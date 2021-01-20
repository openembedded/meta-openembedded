SUMMARY = "Python support for YAML"
DEPENDS += "libyaml ${PYTHON_PN}-cython-native"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6d8242660a8371add5fe547adf083079"

PYPI_PACKAGE = "PyYAML"

inherit pypi setuptools3

SRC_URI[sha256sum] = "3c49e39ac034fd64fd576d63bb4db53cda89b362768a67f07749d55f128ac18a"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-netclient \
"

BBCLASSEXTEND = "native nativesdk"
