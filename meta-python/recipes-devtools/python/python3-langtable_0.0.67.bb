DESCRIPTION = "langtable is used to guess reasonable defaults for locale,\
keyboard, territory"
HOMEPAGE = "https://github.com/mike-fabian/langtable/"
LICENSE = "GPL-3.0-or-later"
SECTION = "devel/python"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI[sha256sum] = "04c81012b5a9f018567a480bff0b7228e8db95e86fea0eb3d45894d8b8b80e64"

inherit pypi setuptools3 python3native

DISTUTILS_INSTALL_ARGS += " \
    --install-data=${datadir}/langtable"

FILES:${PN} += "${datadir}/*"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-doctest \
    python3-logging \
    python3-xml \
"
