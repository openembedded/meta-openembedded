SUMMARY = "golden master/snapshot/approval testing library which puts the values right into your source code"
HOMEPAGE = "https://15r10nk.github.io/inline-snapshot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a35eb90dfdf03953dd2074d0fdba1d4"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "6aa304f2904103a449f3213e059728da5f1c34757f943efca837b4669f290843"

inherit pypi python_hatchling

PYPI_PACKAGE = "inline_snapshot"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

RDEPENDS:${PN} = "python3-asttokens \
                  python3-black \
                  python3-click \
                  python3-executing \
                  python3-rich \
                  python3-tomli \
                  python3-typing-extensions \
                  "
