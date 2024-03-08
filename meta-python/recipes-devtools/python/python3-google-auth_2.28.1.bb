DESCRIPTION = "Google Authentication Library"
HOMEPAGE = "https://github.com/googleapis/google-auth-library-python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit pypi setuptools3 ptest

SRC_URI[sha256sum] = "34fc3046c257cedcf1622fc4b31fc2be7923d9b4d44973d481125ecc50d83885"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-cryptography \
        python3-flask \
        python3-freezegun \
        python3-grpcio \
        python3-mock \
        python3-oauth2client \
        python3-pyopenssl \
        python3-pytest \
        python3-pytest-localserver \
        python3-pyu2f \
        python3-requests \
        python3-responses \
        python3-unittest-automake-output \
        python3-unixadmin \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += "\
        python3-cachetools \
        python3-json \
        python3-pyasn1-modules \
        python3-rsa \
"
