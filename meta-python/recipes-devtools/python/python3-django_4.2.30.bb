require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "4ebc7a434e3819db6cf4b399fb5b3f536310a30e8486f08b66886840be84b37c"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch,
# PREFERRED_VERSION_python3-django = "4.2.%" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
