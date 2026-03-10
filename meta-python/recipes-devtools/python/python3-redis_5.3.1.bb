SUMMARY = "Python client for Redis key-value store"
DESCRIPTION = "The Python interface to the Redis key-value store."
HOMEPAGE = "https://github.com/andymccurdy/redis-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=609ded3add9111c4c6e102f1d421d3f8"

# Prefix archive to avoid clashing with the main redis archives
PYPI_ARCHIVE_NAME_PREFIX = "pypi-"

SRC_URI[sha256sum] = "ca49577a531ea64039b5a36db3d6cd1a0c7a60c34124d46924a45b956e8cf14c"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-cryptography \
    python3-datetime \
    python3-json \
    python3-packaging \
"

CVE_PRODUCT = "redis-py"
