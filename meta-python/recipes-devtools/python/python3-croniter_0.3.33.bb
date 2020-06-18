DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"
SRC_URI[sha256sum] = "03ad19baa220ca8bd105413c9f3bab2e7a1514046c20aeb7bf16805c07538eee"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil"
