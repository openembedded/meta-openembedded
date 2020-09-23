SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "eda3a8d9aae8459ef6efe540293a9ca8"
SRC_URI[sha256sum] = "a698993f3abbe06e88e8a3c8b61c8a79c12f62e503f1a23eda30c3921f0525a9"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
