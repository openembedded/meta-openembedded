SUMMARY = "Python package for parsing and generating vCard and vCalendar files"
HOMEPAGE = "http://vobject.skyhouseconsulting.com/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[md5sum] = "1da0747914fe440329c0b37e4be9040d"
SRC_URI[sha256sum] = "cd9ede4363f83c06ba8d8f1541c736efa5c46f9a431430002b2f84f4f4e674d8"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-numbers \
    "
