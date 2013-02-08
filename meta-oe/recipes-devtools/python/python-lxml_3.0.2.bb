SUMMARY = "Python XML bindings for libxml2 and libxslt"
DESCRIPTION = "Powerful and Pythonic XML processing library combining \
libxml2/libxslt with the ElementTree API."
HOMEPAGE = "http://codespeak.net/lxml"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=f9f1dc24f720c143c2240df41fe5073b"
SRCNAME = "lxml"

DEPENDS = "libxml2 libxslt"

SRC_URI = "http://pypi.python.org/packages/source/l/${SRCNAME}/${SRCNAME}-${PV}.tar.gz;name=lxml"
SRC_URI[lxml.md5sum] = "38b15b0dd5e9292cf98be800e84a3ce4"
SRC_URI[lxml.sha256sum] = "cadba4cf0e235127795f76a6f7092cb035da23a6e9ec4c93f8af43a6784cd101"

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

RDEPENDS_${PN} += "libxml2 libxslt python-compression"
RDEPENDS_${PN}_virtclass-native = "libxml2-native libxslt-native"

