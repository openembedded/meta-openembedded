SUMMARY  = "Ultra fast JSON encoder and decoder for Python"
DESCRIPTION = "UltraJSON is an ultra fast JSON encoder and decoder written in pure C with bindings for Python 2.5+ and 3."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=5b206f6ba5e41c60df8cdcddf79a3d82"

SRC_URI[md5sum] = "8148a2493fff78940feab1e11dc0a893"
SRC_URI[sha256sum] = "68cf825f227c82e1ac61e423cfcad923ff734c27b5bdd7174495d162c42c602b"

PYPI_PACKAGE = "ujson"
PYPI_PACKAGE_EXT = "zip"
inherit pypi setuptools

RDEPENDS_${PN} += "python-numbers"
