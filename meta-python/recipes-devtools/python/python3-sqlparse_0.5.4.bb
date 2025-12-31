DESCRIPTION = "Non-validating SQL parser module"
HOMEPAGE = "https://pypi.python.org/pypi/sqlparse"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b136f573f5386001ea3b7b9016222fc"

SRC_URI[sha256sum] = "4396a7d3cf1cd679c1be976cf3dc6e0a51d0111e87787e7a8d780e7d5a998f9e"

CVE_PRODUCT = "sqlparse"

export BUILD_SYS
export HOST_SYS

inherit pypi ptest-python-pytest python_hatchling

RDEPENDS:${PN}-ptest += "\
    python3-mypy \
    python3-unixadmin \
"

BBCLASSEXTEND = "native nativesdk"
