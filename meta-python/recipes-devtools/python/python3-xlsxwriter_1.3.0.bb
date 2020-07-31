SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0960d52d61b0801760f39463288c2672"

inherit pypi setuptools3

PYPI_PACKAGE = "XlsxWriter"

SRC_URI[md5sum] = "80ac0ccf43435739c4afa5e0bfff8580"
SRC_URI[sha256sum] = "3015f707cf237d277cf1b2d7805f409f0387e32bc52f3c76db9f85098980e828"

BBCLASSEXTEND = "native nativesdk"
