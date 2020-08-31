SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "ae5d718ad6fca0bf33960825544caae6"
SRC_URI[sha256sum] = "2c770e7bf72d6419bb82b3bb950f3789fc24bd6a0ba191369607a96289acd63b"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
