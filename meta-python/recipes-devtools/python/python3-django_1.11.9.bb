require python-django.inc
inherit setuptools3

# Requires _pydecimal which is in misc
RDEPENDS_${PN} += " \
    ${PYTHON_PN}-misc \
"
