DESCRIPTION = "DNS toolkit for Python"
HOMEPAGE = "https://www.dnspython.org/"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5af50906b5929837f667dfe31052bd34"

SRC_URI[sha256sum] = "8d4d39a513e649d634004ce4d39ade2352efcea472a0247535a5c2d18a9d7ba9"

CVE_PRODUCT = "dnspython"

inherit pypi python_uv_build ptest

SRC_URI += " \
	file://run-ptest \
	file://0001-tests-skip-DDR-tests-when-the-resolvers-do-not-answe.patch \
	file://0001-tests-retry-socket-timeouts-in-retry_on_timeout.patch \
"

RDEPENDS:${PN}-ptest += " \
    python3-idna \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

DEPENDS += "\
    python3-wheel-native \
    python3-setuptools-scm-native \
"

RDEPENDS:${PN} += " \
    python3-crypt \
    python3-io \
    python3-math \
    python3-netclient \
    python3-numbers \
    python3-threading \
"
