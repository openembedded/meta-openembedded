SUMMARY = "A comprehensive HTTP client library, httplib2 supports many features left out of other HTTP libraries."
HOMEPAGE = "https://github.com/httplib2/httplib2"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=56e5e931172b6164b62dc7c4aba6c8cf"

SRC_URI[sha256sum] = "21591655ac54953624c6ab8d587c71675e379e31e2cfe3147c83c11e9ef41f92"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-compression \
    python3-netclient \
    python3-pyparsing \
"

CVE_PRODUCT = "httplib2"
