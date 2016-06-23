DESCRIPTION = "Python HTTP for Humans."
HOMEPAGE = "http://python-requests.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9bb3515869c0f426cb8441c899ae7f5"

SRC_URI[md5sum] = "a36f7a64600f1bfec4d55ae021d232ae"
SRC_URI[sha256sum] = "63f1815788157130cee16a933b2ee184038e975f0017306d723ac326b5525b54"

RDEPENDS_${PN} = "${PYTHON_PN}-email ${PYTHON_PN}-json ${PYTHON_PN}-netserver"
