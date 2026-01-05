SUMMARY = "Send file to trash natively under Mac OS X, Windows and Linux"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a02659c2d5f4cc626e4dcf6504b865eb"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "1761421da3f9930bfe51ed7c45343948573383ad4c27e3acebc91be324e7770d"

PYPI_PACKAGE = "send2trash"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

RDEPENDS:${PN} += "\
    python3-io \
    python3-datetime \
"
