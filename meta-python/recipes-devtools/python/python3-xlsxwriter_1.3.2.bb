SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0960d52d61b0801760f39463288c2672"

inherit pypi setuptools3

PYPI_PACKAGE = "XlsxWriter"

SRC_URI[md5sum] = "78fccd0e056f3c1c474e13eec54ce30d"
SRC_URI[sha256sum] = "3ba4655713c8c84b428b529c8e353ac028626ec57f348094da4f66fdc573b0fb"

BBCLASSEXTEND = "native nativesdk"
