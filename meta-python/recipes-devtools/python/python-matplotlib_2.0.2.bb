DESCRIPTION = "matplotlib is a python 2D plotting library which produces publication quality figures in a variety of hardcopy formats"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE/LICENSE;md5=afec61498aa5f0c45936687da9a53d74"

DEPENDS += "python-numpy freetype libpng python-dateutil python-pytz"
RDEPENDS_${PN} = "python-core python-distutils python-numpy freetype libpng python-dateutil python-pytz"

SRCREV = "e175a41cb81880dbc553d9140e6ae5717457afa8"
SRC_URI = "git://github.com/matplotlib/matplotlib \
           file://fix_setupext.patch \
"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-docs"

inherit setuptools pkgconfig

