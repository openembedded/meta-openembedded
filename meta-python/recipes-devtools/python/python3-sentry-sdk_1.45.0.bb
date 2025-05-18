SUMMARY = "The new Python SDK for Sentry.io"
DESCRIPTION = "This is the next line of the Python SDK \
for Sentry, intended to replace the raven package on PyPI."
HOMEPAGE = "https://github.com/getsentry/sentry-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=03dc788d9a9486be5e6a1d99c2c1ce3a"

RDEPENDS:${PN} += "\
	python3-urllib3 \
	python3-core \
	python3-json \
	python3-logging \
	python3-threading \
	python3-compression \
	python3-datetime \
"

SRC_URI[sha256sum] = "509aa9678c0512344ca886281766c2e538682f8acfa50fd8d405f8c417ad0625"

PYPI_PACKAGE = "sentry-sdk"

inherit pypi setuptools3
