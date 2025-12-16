require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "16b5ccfc5e8c27e6c0561af551d2ea32852d7352c67d452ae3e76b4f6b2ca495"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
