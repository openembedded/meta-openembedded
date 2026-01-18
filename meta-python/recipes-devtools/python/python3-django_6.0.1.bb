require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "ed76a7af4da21551573b3d9dfc1f53e20dd2e6c7d70a3adc93eedb6338130a5f"

# Set DEFAULT_PREFERENCE so that the LTS version of django is built by
# default. To build the 6.x branch,
# PREFERRED_VERSION_python3-django = "6.0.%" can be added to local.conf
DEFAULT_PREFERENCE = "-1"
