require python-django.inc
inherit setuptools3

# Windows-specific DoS via NFKC normalization, not applicable to Linux
CVE_STATUS[CVE-2025-27556] = "not-applicable-platform: Issue only applies on Windows"

SRC_URI = "file://CVE-2025-26699.patch \
           "
SRC_URI[sha256sum] = "e7d98fa05ce09cb3e8d5ad6472fb602322acd1740bfdadc29c8404182d664f65"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
