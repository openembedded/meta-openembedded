DESCRIPTION = "Powerful and Pythonic XML processing library combining \
libxml2/libxslt with the ElementTree API."
HOMEPAGE = "http://codespeak.net/lxml"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=7de92baeb3b7bfaebe72404155fdb346"
SRCNAME = "lxml"
PR = "r1"

DEPENDS = "libxml2 libxslt"
RDEPENDS_${PN} += "libxml2 libxslt python-compression"

SRC_URI = "http://pypi.python.org/packages/source/l/${SRCNAME}/${SRCNAME}-${PV}.tar.gz;name=lxml"
SRC_URI[lxml.md5sum] = "d6c612d63a84d79440912a1b29d3b981"
SRC_URI[lxml.sha256sum] = "89b73925b5e3295dcf4675cb948007a20eb029fe35c9a162ae19ec11f1abafe5"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DISTUTILS_BUILD_ARGS += " \
                     --with-xslt-config='${STAGING_BINDIR_NATIVE}/pkg-config libxslt' \
                     --with-xml2-config='${STAGING_BINDIR_CROSS}/xml2-config' \
"

DISTUTILS_INSTALL_ARGS += " \
                     --with-xslt-config='${STAGING_BINDIR_NATIVE}/pkg-config libxslt' \
                     --with-xml2-config='${STAGING_BINDIR_CROSS}/xml2-config' \
"

BBCLASSEXTEND = "native nativesdk"
RDEPENDS_${PN}_virtclass-native = "libxml2-native libxslt-native"



