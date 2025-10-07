require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "da5e00372763193d73cecbf71084a3848458cecf4cee36b9a1e8d318d114a87b"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
