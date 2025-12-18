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
            file://CVE-2024-38875.patch \
            file://CVE-2023-23969.patch \
            file://CVE-2024-39614.patch \
            file://CVE-2024-41989-0001.patch \
            file://CVE-2024-41989-0002.patch \
            file://CVE-2024-41989-0003.patch \
            file://CVE-2024-41989-0004.patch \
            file://CVE-2024-41990.patch \
            file://CVE-2024-41991.patch \
            file://CVE-2024-45230.patch \
            file://CVE-2024-45231.patch \
            file://CVE-2024-53907.patch \
            file://CVE-2024-27351.patch \
            file://CVE-2025-26699.patch \
            file://CVE-2024-56374.patch \
            file://CVE-2025-57833.patch \
            file://CVE-2024-39329.patch \
            file://CVE-2024-39330.patch \
            file://CVE-2025-32873.patch \
            file://CVE-2025-64459.patch \
           "

SRC_URI[sha256sum] = "0200b657afbf1bc08003845ddda053c7641b9b24951e52acd51f6abda33a7413"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
