require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "40cd7d3f53bc6cd1902eadce23c337e97200888df41e4a73b42d682f23e71d80"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.%" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
