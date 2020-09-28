SUMMARY = "Screen-scraping library"
HOMEPAGE = " https://www.crummy.com/software/BeautifulSoup/bs4"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=f2d38d8a40bf73fd4b3d16ca2e5882d1"

SRC_URI[md5sum] = "5672f9b9bdfb0c6ab3ef074396bf59aa"
SRC_URI[sha256sum] = "1edf5e39f3a5bc6e38b235b369128416c7239b34f692acccececb040233032a1"

inherit pypi setuptools3

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-html5lib \
    ${PYTHON_PN}-lxml \
    ${PYTHON_PN}-soupsieve \
"

BBCLASSEXTEND = "native nativesdk"
