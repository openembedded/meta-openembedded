SUMMARY = "Provides cryptographic recipes and primitives to Python developers"
SECTION = "devel/python"
LICENSE = "Apache-2.0 | BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c3617db4fb6fae01f1d253ab91511e4"
DEPENDS += " python-cffi-native python-cffi python-enum34 python-six python-pyasn1"
SRCNAME = "cryptography"

SRC_URI = "https://pypi.python.org/packages/source/c/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
           file://run-ptest \
           file://build_fix_openssl_1.0.1g.patch \
           file://Remove_SSLv2_bindings.patch \
           file://Comment_lingering_SSLv2_symbol.patch \
"
S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "70dde78a5515abdbfd7a3d58f15689ab"
SRC_URI[sha256sum] = "f4e041bc83c1be94d87116a7aa201c378b7c6581be4d83994b2da0a84499f73b"

inherit pypi

RDEPENDS_${PN} = "\
                  python-pyasn1\
                  python-six\
                  python-cffi\
                  python-enum34\
                  python-setuptools\
                  python-pycparser\
                  python-subprocess\
                  python-threading\
                  python-numbers\
                  python-contextlib\
"

RDEPENDS_${PN}-ptest = "\
                       ${PN}\
                       python-pytest\
                       python-pretend\
                       python-iso8601\
                       python-cryptography-vectors\
"

inherit ptest

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
    install -d ${D}${PTEST_PATH}/tests/hazmat
    cp -rf ${S}/tests/hazmat/* ${D}${PTEST_PATH}/tests/hazmat/
}
