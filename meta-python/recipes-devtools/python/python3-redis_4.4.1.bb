SUMMARY = "Python client for Redis key-value store"
DESCRIPTION = "The Python interface to the Redis key-value store."
HOMEPAGE = "http://github.com/andymccurdy/redis-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1db1f331d351900707368237cc4880cf"

SRC_URI[sha256sum] = "f7a870c44868ab87bbecd6211c6d7c8720b1e9a796b743fbc4725d7ec75651c3"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-cryptography \
    ${PYTHON_PN}-packaging \
    ${PYTHON_PN}-asyncio \
"
