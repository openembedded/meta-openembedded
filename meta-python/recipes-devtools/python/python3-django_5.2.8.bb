require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "23254866a5bb9a2cfa6004e8b809ec6246eba4b58a7589bc2772f1bcc8456c7f"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
