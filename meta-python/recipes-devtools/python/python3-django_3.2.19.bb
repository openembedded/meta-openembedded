require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "031365bae96814da19c10706218c44dff3b654cc4de20a98bd2d29b9bde469f0"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 3.x branch, 
# PREFERRED_VERSION_python3-django = "3.2.2" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
