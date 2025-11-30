require python-django.inc
#From 4.2.22 onwards setuptools configuration migrated to pyproject.toml
inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "9398e487bcb55e3f142cb56d19fbd9a83e15bb03a97edc31f408361ee76d9d7a"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# PYPI package name changed from Django -> django
PYPI_PACKAGE = "django"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 4.x branch, 
# PREFERRED_VERSION_python3-django = "4.2.26" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
