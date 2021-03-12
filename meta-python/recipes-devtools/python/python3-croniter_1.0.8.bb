DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

PYPI_PACKAGE = "croniter"

SRC_URI[sha256sum] = "c8b830d787c9993361b74eaad7d7396090d7f2d9db41ceb4a52cb75da461c3ed"

inherit pypi setuptools3

RDEPENDS_${PN} += " python3-dateutil python3-natsort"
