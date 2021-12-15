require python-django.inc

# Pin to 2.2.x LTS releases ONLY for this recipe
UPSTREAM_CHECK_REGEX = "/${PYPI_PACKAGE}/(?P<pver>(2\.2\.\d*)+)/"

inherit setuptools3

SRC_URI[sha256sum] = "b1e65eaf371347d4b13eb7e061b09786c973061de95390c327c85c1e2aa2349c"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
