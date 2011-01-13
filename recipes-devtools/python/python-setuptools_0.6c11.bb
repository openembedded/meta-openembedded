DESCRIPTION = "Download, build, install, upgrade, and uninstall Python packages"
HOMEPAGE = "http://cheeseshop.python.org/pypi/setuptools"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://setup.py;beginline=23;endline=23;md5=8a314270dd7a8dbca741775415f1716e"

SRCNAME = "setuptools"
PR = "ml2"
DEPENDS += "python"
DEPENDS_virtclass-native += "python-native"

SRC_URI = "\
  http://cheeseshop.python.org/packages/source/s/setuptools/${SRCNAME}-${PV}.tar.gz\
"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

do_install_prepend() {
    install -d ${D}/${libdir}/${PYTHON_DIR}/site-packages
}

RDEPENDS_${PN} = "\
  python-distutils \
  python-compression \
"

SRC_URI[md5sum] = "7df2a529a074f613b509fb44feefe74e"
SRC_URI[sha256sum] = "630fea9b726320b73ee3ca6ff61732cb32675b0389be658080fe46383b87a1d3"

BBCLASSEXTEND = "native"

NATIVE_INSTALL_WORKS = "1"
