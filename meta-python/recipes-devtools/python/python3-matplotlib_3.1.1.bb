SUMMARY = "matplotlib: plotting with Python"
DESCRIPTION = "\
Matplotlib is a Python 2D plotting library which produces \
publication-quality figures in a variety of hardcopy formats \
and interactive environments across platforms."
HOMEPAGE = "https://github.com/matplotlib/matplotlib"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "\
    file://setup.py;beginline=275;endline=275;md5=2a114620e4e6843aa7568d5902501753 \
    file://LICENSE/LICENSE;md5=afec61498aa5f0c45936687da9a53d74 \
"
DEPENDS = "\
    freetype \
    libpng \
    python3-numpy-native \
    python3-dateutil-native \
    python3-pytz-native \
"

SRC_URI[md5sum] = "f894af5564a588e880644123237251b7"
SRC_URI[sha256sum] = "1febd22afe1489b13c6749ea059d392c03261b2950d1d45c17e3aed812080c93"

inherit pypi setuptools3 pkgconfig

RDEPENDS_${PN} = "\
    freetype \
    libpng \
    python3-numpy \
    python3-pyparsing \
    python3-cycler \
    python3-dateutil \
    python3-kiwisolver \
    python3-pytz \
"

BBCLASSEXTEND = "native"
