SUMMARY = "PIP is a tool for installing and managing Python packages"
LICENSE = "MIT & LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=25fba45109565f87de20bae85bc39452"

SRC_URI[md5sum] = "87083c0b9867963b29f7aba3613e8f4a"
SRC_URI[sha256sum] = "4d24b03ffa67638a3fa931c09fd9e0273ffa904e95ebebe7d4b1a54c93d7b732"
PYPI_PACKAGE_HASH = "e7a87556133689add8d1a54c0b14aeff0acb03c64707ce100ecd53934da1aa13"

inherit pypi setuptools

# Since PIP is like CPAN for PERL we need to drag in all python modules to ensure everything works
RDEPENDS_${PN} = "python-modules python-distribute"
