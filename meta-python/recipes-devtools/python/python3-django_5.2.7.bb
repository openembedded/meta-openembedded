require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "e0f6f12e2551b1716a95a63a1366ca91bbcd7be059862c1b18f989b1da356cdd"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
