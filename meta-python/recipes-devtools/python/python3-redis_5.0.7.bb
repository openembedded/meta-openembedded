SUMMARY = "Python client for Redis key-value store"
DESCRIPTION = "The Python interface to the Redis key-value store."
HOMEPAGE = "http://github.com/andymccurdy/redis-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=609ded3add9111c4c6e102f1d421d3f8"

# Prefix archive to avoid clashing with the main redis archives
PYPI_ARCHIVE_NAME_PREFIX="pypi-"

SRC_URI[sha256sum] = "8f611490b93c8109b50adc317b31bfd84fff31def3475b92e7e80bf39f48175b"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-cryptography \
    python3-datetime \
    python3-json \
    python3-packaging \
"
