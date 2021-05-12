require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "0a1d195ad65c52bf275b8277b3d49680bd1137a5f55039a806f25f6b9752ce3d"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 3.x branch, 
# PREFERRED_VERSION_python3-django = "3.2.2" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
