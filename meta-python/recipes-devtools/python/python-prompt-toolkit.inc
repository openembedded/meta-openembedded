SUMMARY = "Library for building powerful interactive command lines in Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b2cde7da89f0c1f3e49bf968d00d554f"

SRC_URI[md5sum] = "9e0be24993b13b204e04ffd20b2873d2"
SRC_URI[sha256sum] = "fa8f0c3668afbc85abbb4601ebc8db1db9d5009c8a833feea107fc63f1e7ef22"

PYPI_PACKAGE = "prompt_toolkit"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-terminal \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-wcwidth \
"

BBCLASSEXTEND = "native nativesdk"
