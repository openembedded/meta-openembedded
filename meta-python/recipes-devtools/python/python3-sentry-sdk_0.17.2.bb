SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "a9d68b19c457d6a9ff70396ab341fca2"
SRC_URI[sha256sum] = "bbfe5633aee4dacb53d79d303ab6bfacf1749fb717750c112fb1658e5accce0d"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
