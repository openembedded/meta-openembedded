require python-django.inc
inherit setuptools3

SRC_URI[md5sum] = "dca447b605dcabd924ac7ba17680cf73"
SRC_URI[sha256sum] = "db2214db1c99017cbd971e58824e6f424375154fe358afc30e976f5b99fc6060"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-sqlparse \
"
