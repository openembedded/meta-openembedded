DESCRIPTION = "croniter provides iteration for datetime object with cron like format"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8ee59850b882cbf623188489ea748e2"

SRC_URI += "file://0001-Allow-using-newer-versions-of-trove-classifiers-and-.patch"
SRC_URI[sha256sum] = "a0b2568303c774f6a4ea2d4ce2e72b1ee911029c7070d64a1befe05cdb107f5a"

inherit pypi python_hatchling

DEPENDS += "python3-pathspec python3-trove-classifiers"
RDEPENDS:${PN} += " \
	python3-dateutil \
	python3-natsort \
	python3-pytz \
"
