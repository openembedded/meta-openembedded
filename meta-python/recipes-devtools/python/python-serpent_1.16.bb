SUMMARY = "Serialization based on ast.literal_eval"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=506280bc092b1a0be25c79fb0ed4aaa6"

SRC_URI[md5sum] = "9d0cfe1f57f468100d3ae94c4de47950"
SRC_URI[sha256sum] = "c26e98c2155228a69eb0dc14ba7b888cb70099b6c4ca47fd9d1f099f7d561c3e"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-numbers \
    "    
