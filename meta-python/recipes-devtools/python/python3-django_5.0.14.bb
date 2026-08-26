require python-django.inc
inherit setuptools3

# Windows-specific DoS via NFKC normalization, not applicable to Linux
CVE_STATUS[CVE-2025-27556] = "not-applicable-platform: Issue only applies on Windows"

SRC_URI += "file://CVE-2025-64460.patch \
            file://CVE-2025-64459-1.patch \
            file://CVE-2025-64459-2.patch \
            file://CVE-2025-57833.patch \
            file://CVE-2025-59681.patch \
            file://CVE-2026-15307.patch \
            file://CVE-2026-15337.patch \
            file://CVE-2026-15830.patch \
           "
SRC_URI[sha256sum] = "29019a5763dbd48da1720d687c3522ef40d1c61be6fb2fad27ed79e9f655bc11"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
