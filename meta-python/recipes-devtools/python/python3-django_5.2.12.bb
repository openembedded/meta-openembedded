require python3-django.inc
inherit python_setuptools_build_meta

SRC_URI[sha256sum] = "6b809af7165c73eff5ce1c87fdae75d4da6520d6667f86401ecf55b681eb1eeb"

RDEPENDS:${PN} += "\
    python3-sqlparse \
    python3-asgiref \
"
