inherit setuptools
require python-asn1crypto.inc

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-textutils \ 
"
