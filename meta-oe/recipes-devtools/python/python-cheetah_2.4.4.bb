DESCRIPTION = "Python template engine and code generation tool"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "MIT"
SRCNAME = "Cheetah"
PR = "r1"

SRC_URI = "http://pypi.python.org/packages/source/C/Cheetah/Cheetah-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} = "python-pickle python-pprint"

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "853917116e731afbc8c8a43c37e6ddba"
SRC_URI[sha256sum] = "be308229f0c1e5e5af4f27d7ee06d90bb19e6af3059794e5fd536a6f29a9b550"
