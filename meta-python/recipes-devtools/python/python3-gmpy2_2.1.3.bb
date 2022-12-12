SUMMARY = "GMP/MPIR, MPFR, and MPC interface to Python 2.6+ and 3.x"
SECTION = "devel/python"
LICENSE = "GPL-3.0-only | LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LESSER;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS += "gmp mpfr libmpc"

PYPI_PACKAGE = "gmpy2"
SRC_URI[sha256sum] = "10360cc39f1eb56e3a833a4d1cae3884c7f65b6ec490994801e6939dc08870ae"

inherit pypi setuptools3 python3native

BBCLASSEXTEND = "native nativesdk"
