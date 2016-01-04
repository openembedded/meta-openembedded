SUMMARY = "Simple, fast, extensible JSON encoder/decoder for Python"
HOMEPAGE = "http://cheeseshop.python.org/pypi/simplejson"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c6338d7abd321c0b50a2a547e441c52e"
PR = "r1"

SRCNAME = "simplejson"

SRC_URI = "http://cheeseshop.python.org/packages/source/s/simplejson/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "b8441f1053edd9dc335ded8c7f98a974"
SRC_URI[sha256sum] = "428ac8f3219c78fb04ce05895d5dff9bd813c05a9a7922c53dc879cd32a12493"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

RDEPENDS_${PN} = "\
    python-core \
    python-re \
    python-io \
    python-netserver \
    python-numbers \
"


