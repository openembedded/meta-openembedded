SUMMARY = "World timezone definitions, modern and historical"
HOMEPAGE = " http://pythonhosted.org/pytz"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22b38951eb857cf285a4560a914b7cd6"

SRC_URI[md5sum] = "8660ba7c3c0abd23e6e4efa493b02966"
SRC_URI[sha256sum] = "8787de03f35f31699bcaf127e56ad14c00647965ed24d72dbaca87c6e4f843a3"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-threading \
"
