SUMMARY = "Python client for Redis key-value store"
DESCRIPTION = "The Python interface to the Redis key-value store."
HOMEPAGE = "https://github.com/andymccurdy/redis-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=609ded3add9111c4c6e102f1d421d3f8"

# Prefix archive to avoid clashing with the main redis archives
PYPI_ARCHIVE_NAME_PREFIX = "pypi-"

SRC_URI[sha256sum] = "58b1493d83ba8f0e3ffe6bd781194ca5c01c91267ae03d29c1087b0b3c7d79c9"

inherit pypi python_hatchling

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-cryptography \
    python3-datetime \
    python3-json \
    python3-packaging \
"

CVE_PRODUCT = "redis-py"
