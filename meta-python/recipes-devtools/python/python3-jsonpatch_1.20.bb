inherit setuptools3
require python-jsonpatch.inc

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-enum \
"
