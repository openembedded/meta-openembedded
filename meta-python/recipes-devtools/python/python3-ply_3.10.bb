require python-ply.inc
inherit setuptools3

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-enum \
"
