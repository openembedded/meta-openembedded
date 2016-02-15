SUMMARY = "World timezone definitions, modern and historical"
HOMEPAGE = " http://pythonhosted.org/pytz"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22b38951eb857cf285a4560a914b7cd6"

inherit pypi setuptools

RDEPENDS_${PN} = "\
    python-core \
    python-datetime \
"

SRC_URI[md5sum] = "417a47b1c432d90333e42084a605d3d8"
SRC_URI[sha256sum] = "c4ee70cb407f9284517ac368f121cf0796a7134b961e53d9daf1aaae8f44fb90"
