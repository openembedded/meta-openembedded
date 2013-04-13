DESCRIPTION = "Templating library for Python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=da8dd26ed9751ee0cfdf9df1a16bbb54"

PR = "r2"

SRC_URI = "http://www.makotemplates.org/downloads/Mako-${PV}.tar.gz"
SRC_URI[md5sum] = "e3c0a677aa4216da9e89ef8fa76cbafb"
SRC_URI[sha256sum] = "fe8698e845035586bd711a6748e4e40a208a58de276b9138026164700494b68f"

S = "${WORKDIR}/Mako-${PV}"

inherit setuptools

RDEPENDS_${PN} = "python-threading \
                  python-netclient \
                  python-html \
"
