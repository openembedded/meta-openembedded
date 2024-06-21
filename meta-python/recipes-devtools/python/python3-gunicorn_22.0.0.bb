SUMMARY = "WSGI HTTP Server for UNIX"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5b70a8b30792a916f50dc96123e61ddf"

SRC_URI[sha256sum] = "4a0b436239ff76fb33f11c07a16482c521a7e09c1ce3cc293c2330afe01bec63"

inherit pypi python_setuptools_build_meta ptest

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
    python3-eventlet \
    python3-gevent \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += "python3-setuptools python3-fcntl"
