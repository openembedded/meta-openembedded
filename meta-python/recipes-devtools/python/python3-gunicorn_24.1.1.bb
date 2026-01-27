SUMMARY = "WSGI HTTP Server for UNIX"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5b70a8b30792a916f50dc96123e61ddf"

SRC_URI[sha256sum] = "f006d110e5cb3102859b4f5cd48335dbd9cc28d0d27cd24ddbdafa6c60929408"

inherit pypi python_setuptools_build_meta ptest

CVE_PRODUCT = "gunicorn"

SRC_URI += " \
	file://run-ptest \
"

# python-misc for wsgiref
RDEPENDS:${PN}-ptest += " \
    python3-eventlet \
    python3-gevent \
    python3-misc \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += "python3-setuptools python3-fcntl"
