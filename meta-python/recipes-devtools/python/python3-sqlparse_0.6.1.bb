DESCRIPTION = "Non-validating SQL parser module"
HOMEPAGE = "https://pypi.python.org/pypi/sqlparse"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b136f573f5386001ea3b7b9016222fc"

SRC_URI[sha256sum] = "ca722573f848ff2d30e40ea30f53a104b7dea7635ea9285891263eb2c58fb6e7"

CVE_PRODUCT = "sqlparse"

export BUILD_SYS
export HOST_SYS

inherit pypi ptest-python-pytest python_hatchling

RDEPENDS:${PN}-ptest += "\
    python3-mypy \
    python3-unixadmin \
"

BBCLASSEXTEND = "native nativesdk"
