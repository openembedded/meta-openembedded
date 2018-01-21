require python-django.inc
inherit setuptools

RDEPENDS_${PN} += "${PYTHON_PN}-argparse ${PYTHON_PN}-zlib"
