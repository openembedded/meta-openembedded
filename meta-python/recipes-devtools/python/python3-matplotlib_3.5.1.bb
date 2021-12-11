SUMMARY = "matplotlib: plotting with Python"
DESCRIPTION = "\
Matplotlib is a Python 2D plotting library which produces \
publication-quality figures in a variety of hardcopy formats \
and interactive environments across platforms."
HOMEPAGE = "https://github.com/matplotlib/matplotlib"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "\
    file://setup.py;beginline=296;endline=296;md5=20e7ab4d2b2b1395a0e4ab800181eb96 \
    file://LICENSE/LICENSE;md5=afec61498aa5f0c45936687da9a53d74 \
"

DEPENDS = "\
    freetype \
    libpng \
    ${PYTHON_PN}-numpy-native \
    ${PYTHON_PN}-pip-native \
    ${PYTHON_PN}-dateutil-native \
    ${PYTHON_PN}-pytz-native \
    ${PYTHON_PN}-certifi-native \
"

SRC_URI[sha256sum] = "b2e9810e09c3a47b73ce9cab5a72243a1258f61e7900969097a817232246ce1c"

inherit pypi setuptools3 pkgconfig

# LTO with clang needs lld
LDFLAGS:append:toolchain-clang = " -fuse-ld=lld"
LDFLAGS:remove:toolchain-clang:mips = "-fuse-ld=lld"

RDEPENDS:${PN} = "\
    freetype \
    libpng \
    ${PYTHON_PN}-numpy \
    ${PYTHON_PN}-pyparsing \
    ${PYTHON_PN}-cycler \
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-kiwisolver \
    ${PYTHON_PN}-pytz \
"

ENABLELTO:toolchain-clang:riscv64 = "echo enable_lto = False >> ${S}/setup.cfg"
ENABLELTO:toolchain-clang:riscv32 = "echo enable_lto = False >> ${S}/setup.cfg"
ENABLELTO:toolchain-clang:mips = "echo enable_lto = False >> ${S}/setup.cfg"
do_compile:prepend() {
    echo [libs] > ${S}/setup.cfg
    echo system_freetype = true >> ${S}/setup.cfg
    ${ENABLELTO}
}

BBCLASSEXTEND = "native"
