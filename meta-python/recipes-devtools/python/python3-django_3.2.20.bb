require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "dec2a116787b8e14962014bf78e120bba454135108e1af9e9b91ade7b2964c40"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 3.x branch, 
# PREFERRED_VERSION_python3-django = "3.2.20" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
