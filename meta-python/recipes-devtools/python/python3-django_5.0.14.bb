require python-django.inc
inherit setuptools3

# Windows-specific DoS via NFKC normalization, not applicable to Linux
CVE_STATUS[CVE-2025-27556] = "not-applicable-platform: Issue only applies on Windows"

SRC_URI[sha256sum] = "29019a5763dbd48da1720d687c3522ef40d1c61be6fb2fad27ed79e9f655bc11"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
