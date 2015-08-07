SUMMARY = "PIP is a tool for installing and managing Python packages"
LICENSE = "MIT & LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=45665b53032c02b35e29ddab8e61fa91"

SRC_URI = "https://pypi.python.org/packages/source/p/pip/pip-${PV}.tar.gz"

SRC_URI[md5sum] = "d935ee9146074b1d3f26c5f0acfd120e"
SRC_URI[sha256sum] = "d5275ba3221182a5dd1b6bcfbfc5ec277fb399dd23226d6fa018048f7e0f10f2"

S = "${WORKDIR}/pip-${PV}"

inherit setuptools

# Since PIP is like CPAN for PERL we need to drag in all python modules to ensure everything works
RDEPENDS_${PN} = "python-modules python-distribute"
