SUMMARY = "Serialization based on ast.literal_eval"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=dd6e89638fc70146d17dd7087fa817ae"

SRC_URI[md5sum] = "6950f234edcacfa1d31b6655ac3c6256"
SRC_URI[sha256sum] = "a67ca57082b00ea734854d751ab19e8a49d4ea0d4be7194985da35863fb9fb19"

inherit pypi

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-numbers \
    "    
