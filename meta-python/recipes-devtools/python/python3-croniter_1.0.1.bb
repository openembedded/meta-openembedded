DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "9d9942beaae10c0f9f5de4dcbfab4d85b10638cf407195b82d990bc086d6de0d"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil python3-natsort"
