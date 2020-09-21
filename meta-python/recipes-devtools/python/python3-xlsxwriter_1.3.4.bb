SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0960d52d61b0801760f39463288c2672"

inherit pypi setuptools3

PYPI_PACKAGE = "XlsxWriter"

SRC_URI[md5sum] = "7ea11261cbd5c763d106eea64471cf2f"
SRC_URI[sha256sum] = "d804881beb944c4fd73deed0ef2666b84437f30d72bdc1b3a6f0230458facbfd"

BBCLASSEXTEND = "native nativesdk"
