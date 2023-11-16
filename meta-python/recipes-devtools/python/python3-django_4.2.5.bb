require python-django.inc
inherit setuptools3

SRC_URI[sha256sum] = "5e5c1c9548ffb7796b4a8a4782e9a2e5a3df3615259fc1bfd3ebc73b646146c1"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
    ${PYTHON_PN}-asgiref \
"
