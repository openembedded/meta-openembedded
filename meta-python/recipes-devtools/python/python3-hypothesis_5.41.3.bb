SUMMARY = "A library for property-based testing"
HOMEPAGE = "https://github.com/HypothesisWorks/hypothesis/tree/master/hypothesis-python"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4ee62c16ebd0f4f99d906f36b7de8c3c"

PYPI_PACKAGE = "hypothesis"

inherit pypi setuptools3

SRC_URI[sha256sum] = "6a3471ff74864ab04a0650c75500ef15f2f4a901d49ccbb7cbec668365736688"

RDEPENDS_${PN} += "python3-core"

BBCLASSEXTEND = "native nativesdk"
