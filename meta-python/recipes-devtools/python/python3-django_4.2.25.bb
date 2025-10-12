require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "2391ab3d78191caaae2c963c19fd70b99e9751008da22a0adcc667c5a4f8d311"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.%" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
