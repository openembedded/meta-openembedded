SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0960d52d61b0801760f39463288c2672"

inherit pypi setuptools3

PYPI_PACKAGE = "XlsxWriter"

SRC_URI[md5sum] = "0b65976905bf3841bc6cae3f8c47295b"
SRC_URI[sha256sum] = "de54547f3aa1a611453e32a8ae46380edb5556e823c6803f8673b4b770e50b3b"

BBCLASSEXTEND = "native nativesdk"
