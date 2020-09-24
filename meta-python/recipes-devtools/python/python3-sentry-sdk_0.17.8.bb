SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "17ad6e414d77026e264152bd4cc11400"
SRC_URI[sha256sum] = "e159f7c919d19ae86e5a4ff370fccc45149fab461fbeb93fb5a735a0b33a9cb1"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
