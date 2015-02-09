SUMMARY = "Python XML bindings for libxml2 and libxslt"
DESCRIPTION = "Powerful and Pythonic XML processing library combining \
libxml2/libxslt with the ElementTree API."
HOMEPAGE = "http://codespeak.net/lxml"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=f9f1dc24f720c143c2240df41fe5073b"
SRCNAME = "lxml"

DEPENDS = "libxml2 libxslt"

SRC_URI = "http://pypi.python.org/packages/source/l/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
		file://python-lxml-3.2.5-fix-CVE-2014-3146.patch "

SRC_URI[md5sum] = "6c4fb9b1840631cff09b8229a12a9ef7"
SRC_URI[sha256sum] = "2bf072808a6546d0e56bf1ad3b98a43cca828724360d7419fad135141bd31f7e"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DISTUTILS_BUILD_ARGS += " \
                     --with-xslt-config='${STAGING_BINDIR_NATIVE}/pkg-config libxslt' \
                     --with-xml2-config='${STAGING_BINDIR_CROSS}/pkg-config libxml2' \
"

DISTUTILS_INSTALL_ARGS += " \
                     --with-xslt-config='${STAGING_BINDIR_NATIVE}/pkg-config libxslt' \
                     --with-xml2-config='${STAGING_BINDIR_CROSS}/xml2-config' \
"

BBCLASSEXTEND = "native nativesdk"

RDEPENDS_${PN} += "libxml2 libxslt python-compression"
RDEPENDS_${PN}_virtclass-native = "libxml2-native libxslt-native"

