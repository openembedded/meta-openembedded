require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "a31589db5188d074c63f0945c3888fad104627dfcc236fb2b97f71f89da33bc4"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
