SUMMARY = "PIP is a tool for installing and managing Python packages"
LICENSE = "MIT & LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=45665b53032c02b35e29ddab8e61fa91"

SRC_URI = "https://pypi.python.org/packages/source/p/pip/pip-${PV}.tar.gz"

SRC_URI[md5sum] = "01026f87978932060cc86c1dc527903e"
SRC_URI[sha256sum] = "b1a4ae66baf21b7eb05a5e4f37c50c2706fa28ea1f8780ce8efe14dcd9f1726c"

S = "${WORKDIR}/pip-${PV}"

inherit setuptools

# Since PIP is like CPAN for PERL we need to drag in all python modules to ensure everything works
RDEPENDS_${PN} = "python-modules python-distribute"
