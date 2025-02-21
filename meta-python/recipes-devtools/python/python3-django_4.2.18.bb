require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "52ae8eacf635617c0f13b44f749e5ea13dc34262819b2cc8c8636abb08d82c4b"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.18" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
