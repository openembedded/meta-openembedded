DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "4023e4d18ced979332369964351e8f4f608c1f7c763e146b1d740002c4245247"

inherit pypi setuptools3

RDEPENDS:${PN} += " python3-dateutil python3-natsort"
