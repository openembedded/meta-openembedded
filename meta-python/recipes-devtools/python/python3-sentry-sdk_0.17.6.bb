SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "f48cccd128b10a9a4d19db89d1a74a58"
SRC_URI[sha256sum] = "1a086486ff9da15791f294f6e9915eb3747d161ef64dee2d038a4d0b4a369b24"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
