SUMMARY = "Library to return tzinfo with the local timezone information"
HOMEPAGE = "https://pypi.org/project/tzlocal/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=57e0bd61643d81d05683cdce65b11d10"

SRC_URI[sha256sum] = "2fafbfc07e9d8b49ade18f898d6bcd37ae88ce3ad6486842a2e4f03af68323d2"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-datetime \
    python3-logging \
    python3-zoneinfo \
"
