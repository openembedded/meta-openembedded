DESCRIPTION = "A utility library for mocking out the requests Python library."
HOMEPAGE = "https://github.com/getsentry/responses"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0e601511a8517f4daf688a8eb95be7a2"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "9374d047a575c8f781b94454db5cab590b6029505f488d12899ddb10a4af1cf4"

RDEPENDS:${PN} += " \
	python3-mock \
	python3-pyyaml \
	python3-requests \
	python3-urllib3 \
"
