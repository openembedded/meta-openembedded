DESCRIPTION = "Provides enhanced HTTPS support for httplib and urllib2 using PyOpenSSL"
HOMEPAGE = "http://python-requests.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://MANIFEST.in;md5=ce22c0cd986d2de3f7073cd6b5523ae0"

SRC_URI[md5sum] = "1c28be0449268359e795b3b5687feeb7"
SRC_URI[sha256sum] = "c009f5430790936b3a97eaf9f968516664d97e146ab2bda991f0caadb7cc088b"

PYPI_PACKAGE = "ndg_httpsclient"

DEPENDS += " \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-pyasn1 \
"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-pyasn1 \
"

BBCLASSEXTEND = "native nativesdk"

UPSTREAM_CHECK_REGEX = ""
