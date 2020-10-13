SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "5c988086e4c124380887b0d579081f33"
SRC_URI[sha256sum] = "a3716e98a1285a74eeaea7418a5b8fb2d7568fa11b5fba389946f465876a4d44"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
