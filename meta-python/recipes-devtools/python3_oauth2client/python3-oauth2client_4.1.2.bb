DESCRIPTION = "OAuth 2.0 client library"

HOMEPAGE = "http://github.com/google/oauth2client/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=038e1390e94fe637991fa5569daa62bc"

inherit pypi

SRC_URI[sha256sum] = "bd3062c06f8b10c6ef7a890b22c2740e5f87d61b6e1f4b1c90d069cdfc9dadb5"

RDEPENDS:${PN} += " \
        ${PYTHON_PN}-httplib2 \
        ${PYTHON_PN}-pyasn1 \
        ${PYTHON_PN}-pyasn1-modules \
        ${PYTHON_PN}-rsa \
        ${PYTHON_PN}-six \
        "

inherit setuptools3
