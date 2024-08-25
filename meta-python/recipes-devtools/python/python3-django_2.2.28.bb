require python-django.inc

# Pin to 2.2.x LTS releases ONLY for this recipe
UPSTREAM_CHECK_REGEX = "/${PYPI_PACKAGE}/(?P<pver>(2\.2\.\d*)+)/"

inherit setuptools3

SRC_URI += "file://CVE-2023-31047.patch \
            file://CVE-2023-36053.patch \
            file://CVE-2023-41164.patch \
            file://CVE-2023-43665.patch \
            file://CVE-2023-46695.patch \
            file://CVE-2024-24680.patch \
            file://CVE-2024-42005.patch \
           "

SRC_URI[sha256sum] = "0200b657afbf1bc08003845ddda053c7641b9b24951e52acd51f6abda33a7413"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
