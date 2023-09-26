require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "a5de4c484e7b7418e6d3e52a5b8794f0e6b9f9e4ce3c037018cf1c489fa87f3c"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 3.x branch, 
# PREFERRED_VERSION_python3-django = "3.2.21" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
