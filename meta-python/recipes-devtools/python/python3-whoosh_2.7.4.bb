inherit pypi setuptools3
require python-whoosh.inc

# Requires _pydecimal.py which is in misc
RDEPENDS_${PN} += " \
    ${PYTHON_PN}-misc \
"
