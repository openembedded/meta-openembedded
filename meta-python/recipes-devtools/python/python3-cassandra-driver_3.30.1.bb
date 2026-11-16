SUMMARY = "DataStax Python Driver for Apache Cassandra"
DESCRIPTION = "A modern, feature-rich and highly-tunable Python client \
library for Apache Cassandra (1.2+) and DataStax Enterprise (3.1+) using \
exclusively Cassandra's binary protocol and Cassandra Query Language v3."
HOMEPAGE = "https://github.com/datastax/python-driver"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"
SRCNAME = "cassandra-driver"

SRC_URI[sha256sum] = "0c6a3e1428f7c6a9aa6c944b9c47a37cd2cdbeb5b5a82d42c33afdd56f14e398"

inherit pypi python_setuptools_build_meta

# Look for libev in the sysroot rather than in host directories
do_configure:prepend() {
    sed -i -e 's|^libev-includes = .*|libev-includes = ["${STAGING_INCDIR}"]|' \
        -e 's|^libev-libs = .*|libev-libs = ["${STAGING_LIBDIR}"]|' ${S}/pyproject.toml
}

RDEPENDS:${PN} += "\
    python3-cython \
    python3-deprecated \
    python3-geomet \
    python3-json \
    python3-misc \
    python3-multiprocessing \
    python3-numbers \
    libevent \
"

DEPENDS += "\
    python3-cython-native \
    python3-tomli-native \
    libev \
"
