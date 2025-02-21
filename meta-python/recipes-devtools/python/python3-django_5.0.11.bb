require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "e7d98fa05ce09cb3e8d5ad6472fb602322acd1740bfdadc29c8404182d664f65"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
