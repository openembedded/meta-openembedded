SUMMARY = "Ctypes-based simple MagickWand API binding for Python"
HOMEPAGE = "https://docs.wand-py.org/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a2b05e6331064556e971cfa0efca0bf"

SRC_URI[sha256sum] = "2325eae60fb21b88e293d5ec8dcdedf2644a0c00eb75024c9b47975e1e902e80"

inherit pypi setuptools3

PYPI_PACKAGE = "wand"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

FILES:${PN}-doc += "${datadir}/README.rst"

BBCLASSEXTEND = "native nativesdk"
