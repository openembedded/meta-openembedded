require python-netaddr.inc
inherit setuptools3

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-enum \
    ${PYTHON_PN}-selectors \
"
