SUMMARY = "Serialization based on ast.literal_eval"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=2669deccb79531c74a5ddd71b45f5095"

SRC_URI[md5sum] = "e2cd7b751274c59b420d4f54db9a49cf"
SRC_URI[sha256sum] = "9afebb58ac8b827050e1969324388349dc9738b0a03b0d91567b328834a27732"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-numbers \
    "    
