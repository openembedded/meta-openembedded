SUMMARY = "Python 2 and 3 compatibility library"
HOMEPAGE = "https://xlsxwriter.readthedocs.io"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4e95e368cd6cb090815046688e92d11e"

inherit pypi setuptools3

PYPI_PACKAGE = "XlsxWriter"

SRC_URI[sha256sum] = "eca2e737d0b8df3cd72520fadcda9f48c3581282639965125a86ea8cd04620cf"

BBCLASSEXTEND = "native nativesdk"
