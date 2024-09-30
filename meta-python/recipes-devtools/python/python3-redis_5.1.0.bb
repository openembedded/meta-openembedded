SUMMARY = "Python client for Redis key-value store"
DESCRIPTION = "The Python interface to the Redis key-value store."
HOMEPAGE = "http://github.com/andymccurdy/redis-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=609ded3add9111c4c6e102f1d421d3f8"

# Prefix archive to avoid clashing with the main redis archives
PYPI_ARCHIVE_NAME_PREFIX="pypi-"

SRC_URI[sha256sum] = "b756df1e4a3858fcc0ef861f3fc53623a96c41e2b1f5304e09e0fe758d333d40"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-cryptography \
    python3-datetime \
    python3-json \
    python3-packaging \
"
