DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "cd89bcbed4d79f56bbf2c9415c2bbbfd09fadc8a59dbae3898fbca5bab34103d"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil python3-natsort"
