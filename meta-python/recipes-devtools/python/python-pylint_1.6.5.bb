inherit setuptools
require python-pylint.inc

RDEPENDS_${PN} += "${PYTHON_PN}-backports-functools-lru-cache"
