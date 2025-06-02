SUMMARY = "FreezeGun is a library that allows your Python tests to travel through time by mocking the datetime module."
HOMEPAGE = "https://github.com/spulec/freezegun"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=acf1d209bb6eddae4cbe6ffd6a0144fe"

SRC_URI[sha256sum] = "a54ae1d2f9c02dbf42e02c18a3ab95ab4295818b549a34dac55592d72a905181"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN}-ptest += " \
        python3-sqlite3 \
"

RDEPENDS:${PN} = "\
        python3-asyncio \
        python3-dateutil \
        python3-unittest \
"
