SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

SRC_URI[md5sum] = "5d4abcc416cbd0b9443bf56588b02b55"
SRC_URI[sha256sum] = "0af429c221670e602f960fca85ca3f607c85510a91f11e8be8f742a978127f78"

PYPI_PACKAGE = "sentry-sdk"
inherit pypi setuptools3
