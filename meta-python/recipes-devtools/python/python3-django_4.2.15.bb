require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "c77f926b81129493961e19c0e02188f8d07c112a1162df69bfab178ae447f94a"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.15" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
