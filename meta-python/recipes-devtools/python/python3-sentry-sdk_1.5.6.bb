SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

RDEPENDS:${PN} += "python3-urllib3"

SRC_URI[sha256sum] = "ac2a50128409d57655279817aedcb7800cace1f76b266f3dd62055d5afd6e098"

PYPI_PACKAGE = "sentry-sdk"

inherit pypi setuptools3
