SUMMARY  = "Appling JSON patches in Python 2.6+ and 3.x"
HOMEPAGE = "https://github.com/stefankoegl/python-json-patch"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=32b15c843b7a329130f4e266a281ebb3"

SRCNAME = "jsonpatch"

SRC_URI = " \
    https://pypi.python.org/packages/source/j/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
"

inherit setuptools

SRC_URI[md5sum] = "76ae3183db5f2a8f85dd7705e9b6f278"
SRC_URI[sha256sum] = "2e1eb457f9c8dd5dae837ca93c0fe5bd2522c9d44b9b380fb1aab2ab4dec04b1"

RDEPENDS_${PN} += "python-re python-json python-jsonpointer"

S = "${WORKDIR}/${SRCNAME}-${PV}"

