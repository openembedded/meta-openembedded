inherit pypi setuptools3
require python-pymongo.inc

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-enum \
    ${PYTHON_PN}-selectors \
"
