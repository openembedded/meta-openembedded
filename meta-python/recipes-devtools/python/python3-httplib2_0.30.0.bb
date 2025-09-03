SUMMARY = "A comprehensive HTTP client library, httplib2 supports many features left out of other HTTP libraries."
HOMEPAGE = "https://github.com/httplib2/httplib2"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=56e5e931172b6164b62dc7c4aba6c8cf"

SRC_URI[sha256sum] = "d5b23c11fcf8e57e00ff91b7008656af0f6242c8886fd97065c97509e4e548c5"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-compression \
    python3-netclient \
    python3-pyparsing \
"
