inherit setuptools
require python-jsonpatch.inc

RDEPENDS_${PN} += "${PYTHON_PN}-re"

SRC_URI += " \
    file://Drop-support-for-EOL-Python-3.3.patch \
"
