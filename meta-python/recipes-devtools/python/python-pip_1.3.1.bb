SUMMARY = "PIP is a tool for installing and managing Python packages"
LICENSE = "MIT & GPL"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cd00425a3465a0e4c81929b94241d3de"

SRC_URI = "https://pypi.python.org/packages/source/p/pip/pip-${PV}.tar.gz"
SRC_URI[md5sum] = "cbb27a191cebc58997c4da8513863153"
SRC_URI[sha256sum] = "145eaa5d1ea1b062663da1f3a97780d7edea4c63c68a37c463b1deedf7bb4957"

S = "${WORKDIR}/pip-${PV}"

inherit setuptools

# Since PIP is like CPAN for PERL we need to drag in all python modules to ensure everything works
RDEPENDS_${PN} = "python-modules python-distribute"
