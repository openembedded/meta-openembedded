DESCRIPTION = "A libudev binding"
HOMEPAGE = "http://pypi.python.org/pypi/pyudev"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"
PR = "r1"

SRCNAME = "pyudev"
SRC_URI = "http://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "dca4e69da387f9a6683ee4b5c27ca66a"
SRC_URI[sha256sum] = "0acfa500219f4bcf711f35c1b2041d035b4bf5f302eca5038572ee788d9e584a"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} = "\
  python-ctypes \
  python-subprocess \
  python-misc \
  libudev \
"
