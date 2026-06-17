DESCRIPTION = "Provide XPath 1.0 and 2.0 selectors for Python's ElementTree XML data structures, both for the standard ElementTree library and for the lxml.etree library."
HOMEPAGE = "https://github.com/sissaschool/elementpath"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c04ab6ce886cc843af8941bb199406ba"

SRC_URI[sha256sum] = "43d67a38700153eb45349c6e14a4d4b3a06e390c31df06539f730a96baf9a7fc"

PYPI_PACKAGE = "elementpath"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-xml \
    python3-core \
    python3-numbers \
    python3-datetime \
    python3-stringold \
"

BBCLASSEXTEND = "native nativesdk"
