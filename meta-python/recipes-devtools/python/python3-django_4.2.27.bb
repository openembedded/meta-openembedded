require python-django.inc
inherit python_setuptools_build_meta

SRC_URI += "file://0001-lower-setuptools-requirements.patch"
SRC_URI[sha256sum] = "b865fbe0f4a3d1ee36594c5efa42b20db3c8bbb10dff0736face1c6e4bda5b92"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"

PYPI_PACKAGE = "django"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.%" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
