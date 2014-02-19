SUMMARY = "Simple, fast, extensible JSON encoder/decoder for Python"
HOMEPAGE = "http://cheeseshop.python.org/pypi/simplejson"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8a9f8dcfcd28c34daa443e5b1d29813b"
PR = "r1"

SRCNAME = "simplejson"

SRC_URI = "http://cheeseshop.python.org/packages/source/s/simplejson/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "070c6467462bd63306f1756b01df6d70"
SRC_URI[sha256sum] = "e85c5ae24dd9827113893c1dd2c799528195057388096f0fc45ad4b32b378c26"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

RDEPENDS_${PN} = "\
    python-core \
    python-re \
    python-io \
    python-netserver \
    python-numbers \
"


