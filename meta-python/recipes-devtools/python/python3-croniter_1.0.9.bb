DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "708532f70584207e23ef2989ca40f367e6238bfe050133a1aff43e1e0e6f6092"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil python3-natsort"
