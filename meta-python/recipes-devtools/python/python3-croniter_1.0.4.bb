DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "7f861f4a896b2b43069ef218d2b999d2c9f760598c2592a6b842cb04bc75562e"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil python3-natsort"
