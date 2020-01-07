SUMMARY = "Library to process JSON-RPC requests"
HOMEPAGE = "https://github.com/bcb/jsonrpcserver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c89120516900f96f4c60d35fdc4c3f15"

SRC_URI[md5sum] = "c1cc652bdeb04b8ce3ad962fbab34daf"
SRC_URI[sha256sum] = "3a35c0ef21174ca98f995f99688cebadda97053785833fbb31ec862d6b157f6d"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    python3-apply-defaults \
    python3-asyncio \
    python3-core \
    python3-json \
    python3-jsonschema \
    python3-logging \
    python3-netclient \
    python3-pkgutil \
    python3-typing \
"

BBCLASSEXTEND = "native nativesdk"

do_install_append() {
    chmod 0644 ${D}${PYTHON_SITEPACKAGES_DIR}/jsonrpcserver-4.1.0-py3.7.egg-info/*
}
