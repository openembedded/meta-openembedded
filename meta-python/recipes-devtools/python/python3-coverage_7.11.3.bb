SUMMARY = "Code coverage measurement for Python"
HOMEPAGE = "https://coverage.readthedocs.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI[sha256sum] = "0f59387f5e6edbbffec2281affb71cdc85e0776c1745150a3ab9b6c1d016106b"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-crypt \
    python3-io \
    python3-json \
    python3-multiprocessing \
    python3-pprint \
    python3-shell \
    python3-sqlite3 \
    python3-tomllib \
    python3-xml \
"

BBCLASSEXTEND = "native nativesdk"
