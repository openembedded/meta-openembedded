SUMMARY = "World timezone definitions, modern and historical"
HOMEPAGE = " http://pythonhosted.org/pytz"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22b38951eb857cf285a4560a914b7cd6"
SRCNAME = "pytz"

SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

RDEPENDS_${PN} = "\
    python-core \
    python-datetime \
"

SRC_URI[md5sum] = "8940ddae309e44b593c75a9e05dd2a0b"
SRC_URI[sha256sum] = "bfc2bd00147e5ecf75399f4a94cb84cc00ce9b511a15f9958bb6c85a455f76eb"
