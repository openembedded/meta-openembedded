SUMMARY = "Library to process JSON-RPC requests"
HOMEPAGE = "https://github.com/bcb/jsonrpcserver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=61b63ea9d36f6fb63ddaaaac8265304f"

SRC_URI[sha256sum] = "0fdd69c15e67004fb064d2254469cf82d02b7bc8a7bf7760055caa9d62d92f7c"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    python3-apply-defaults \
    python3-asyncio \
    python3-core \
    python3-json \
    python3-jsonschema \
    python3-logging \
    python3-netclient \
    python3-pkgutil \
"

BBCLASSEXTEND = "native nativesdk"
