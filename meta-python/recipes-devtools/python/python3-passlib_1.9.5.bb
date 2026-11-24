SUMMARY = "comprehensive password hashing framework supporting over 30 schemes"
DESCRIPTION = "\
Passlib is a password hashing library for Python 2 & 3, which provides cross-platform \
implementations of over 30 password hashing algorithms, as well as a framework for \
managing existing password hashes. It’s designed to be useful for a wide range of \
tasks, from verifying a hash found in /etc/shadow, to providing full-strength password \
hashing for multi-user applications."
HOMEPAGE = "https://foss.heptapod.net/python-libs/passlib/wikis/home"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c8449c5e10474d113ab787ed2753bafe"

SRC_URI[sha256sum] = "15d39a9b2044a86f341a2e443170e4e9a6d71fe0266fe28b91032a8ca723f8aa"

inherit pypi python_setuptools_build_meta python_hatchling

RDEPENDS:${PN} += "\
    python3-misc \
    python3-crypt \
    python3-logging \
    python3-netclient \
"

BBCLASSEXTEND = "native"
