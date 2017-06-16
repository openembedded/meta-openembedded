SUMMARY = "A Python module for decorators, wrappers and monkey patching."
HOMEPAGE = "http://wrapt.readthedocs.org/"
LICENSE = "BSD"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=82704725592991ea88b042d150a66303"

SRC_URI[md5sum] = "97365e906afa8b431f266866ec4e2e18"
SRC_URI[sha256sum] = "42160c91b77f1bc64a955890038e02f2f72986c01d462d53cb6cb039b995cdd9"

inherit setuptools pypi

RDEPENDS_${PN}_class-target += "\
    ${PYTHON_PN}-stringold \
    ${PYTHON_PN}-threading \
"
