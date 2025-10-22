SUMMARY = "Code coverage measurement for Python"
HOMEPAGE = "https://coverage.readthedocs.io"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI[sha256sum] = "167bd504ac1ca2af7ff3b81d245dfea0292c5032ebef9d66cc08a7d28c1b8050"

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
