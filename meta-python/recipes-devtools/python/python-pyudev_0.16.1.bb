SUMMARY = "A libudev binding"
HOMEPAGE = "http://pypi.python.org/pypi/pyudev"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRCNAME = "pyudev"
SRC_URI = "http://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "4034de584b6d9efcbfc590a047c63285"
SRC_URI[sha256sum] = "765d1c14bd9bd031f64e2612225621984cb2bbb8cbc0c03538bcc4c735ff1c95"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

do_configure_prepend() {
    sed -i "/import pyudev/d" ${S}/setup.py
    sed -i "s/str(pyudev.__version__)/'${PV}'/g" ${S}/setup.py
}

RDEPENDS_${PN} = "\
    python-ctypes \
    python-subprocess \
    python-misc \
"
