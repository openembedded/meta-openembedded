SUMMARY = "matplotlib: plotting with Python"
DESCRIPTION = "\
Matplotlib is a Python 2D plotting library which produces \
publication-quality figures in a variety of hardcopy formats \
and interactive environments across platforms."
HOMEPAGE = "https://github.com/matplotlib/matplotlib"
SECTION = "devel/python"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "\
    file://LICENSE/LICENSE;md5=afec61498aa5f0c45936687da9a53d74 \
"

DEPENDS = "\
    freetype \
    libpng \
    python3-pybind11 \
    qhull \
"

SRC_URI += "file://run-ptest \
            file://0001-Do-not-download-external-dependency-tarballs-via-mes.patch \
            file://0001-Change-types-for-width-height-to-match-definitions-i.patch \
            "
SRC_URI[sha256sum] = "e8d2d0e3881b129268585bf4765ad3ee73a4591d77b9a18c214ac7e3a79fb2ba"

inherit pypi pkgconfig python3targetconfig meson ptest-python-pytest

EXTRA_OEMESON += "-Dsystem-freetype=true -Dsystem-qhull=true"

# LTO with clang needs lld
LDFLAGS:append:toolchain-clang = " -fuse-ld=lld"

FILES:${PN}-ptest += "${PYTHON_SITEPACKAGES_DIR}/matplotlib/tests"
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/"

RDEPENDS:${PN} = "\
    freetype \
    libpng \
    python3-numpy \
    python3-pyparsing \
    python3-cycler \
    python3-dateutil \
    python3-kiwisolver \
    python3-pytz \
    python3-pillow \
    python3-packaging \
"

BBCLASSEXTEND = "native"
