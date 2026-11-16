DESCRIPTION = "Graphviz protocol implementation"
HOMEPAGE = "https://graphviz.readthedocs.io/en/stable/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4b25184bd855af83b70418a1497a1208"

SRC_URI[sha256sum] = "20743e7183be82aaaa8ad6c93f8893c923bd6658a04c32ee115edb3c8a835f78"

inherit pypi python_setuptools_build_meta


RDEPENDS:${PN} += " \
    python3-logging \
"

BBCLASSEXTEND = "native nativesdk"
