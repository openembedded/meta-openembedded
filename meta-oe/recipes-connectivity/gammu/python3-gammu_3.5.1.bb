SUMMARY = "Gammu bindings for Python"
DESCRIPTION = "Python bindings for the Gammu library."
HOMEPAGE = "https://wammu.eu/python-gammu/"
BUGRACKER = "https://github.com/gammu/python-gammu/issues"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PYPI_PACKAGE = "python-gammu"
PYPI_PACKAGE_SDIST = "python_gammu"

inherit pypi python_setuptools_build_meta pkgconfig

SRC_URI += ""
SRC_URI[sha256sum] = "d6082d685983d898f9ab6b401b96a620857bf337bf07e38b80f6b8ab08fe459a"

DEPENDS += "gammu python3-packaging-native"

RDEPENDS:${PN} += "python3-asyncio python3-core python3-threading"

RRECOMMENDS:${PN} += "gammu"
