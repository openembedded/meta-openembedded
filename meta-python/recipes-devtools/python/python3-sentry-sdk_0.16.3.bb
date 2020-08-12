SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "41ac61676030401bcf22b47ac6a2709f"
SRC_URI[sha256sum] = "21b17d6aa064c0fb703a7c00f77cf6c9c497cf2f83345c28892980a5e742d116"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
