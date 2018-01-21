require python-netaddr.inc
inherit setuptools3

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-selectors \
"
