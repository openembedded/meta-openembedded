require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "7ca38a78654aee72378594d63e51636c04b8e28574f5505dff630895b5472777"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
SRC_URI += "\
       file://CVE-2025-26699.patch \
       file://CVE-2024-56374.patch \
       file://CVE-2025-57833.patch \
       file://CVE-2024-39329.patch \
       file://CVE-2024-39330.patch \
       file://CVE-2024-41991.patch \
       file://CVE-2024-53907.patch \
       file://CVE-2025-32873.patch \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 3.x branch, 
# PREFERRED_VERSION_python3-django = "3.2.25" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
