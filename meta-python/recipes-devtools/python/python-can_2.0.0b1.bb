require python-can.inc
inherit pypi setuptools

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-zlib \
"    
