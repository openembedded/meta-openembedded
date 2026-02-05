SUMMARY = "RADIUS tools"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=53dbfa56f61b90215a9f8f0d527c043d"

PYPI_PACKAGE = "pyrad"
SRC_URI[sha256sum] = "368e8d3cb7b9e2ca9e4357b343787238bf135edd3f3598cf26ccd65d32506ff9"

inherit pypi python_poetry_core

RDEPENDS:${PN} += " \
    python3-crypt \
    python3-io \
    python3-logging \
    python3-netaddr \
    python3-six \
"

CVE_PRODUCT = "pyrad"
