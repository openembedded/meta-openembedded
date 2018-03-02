DESCRIPTION = "ZeroMQ looks like an embeddable networking library but acts like a concurrency framework"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=d5311495d952062e0e4fbba39cbf3de1"

PACKAGECONFIG ??= "libsodium"
PACKAGECONFIG[libsodium] = "--with-libsodium, --without-libsodium, libsodium"

SRC_URI = "http://github.com/zeromq/libzmq/releases/download/v${PV}/zeromq-${PV}.tar.gz \
    file://run-ptest \
"
SRC_URI[md5sum] = "c4e18bd0584ccd34213f152c11f54d6c"
SRC_URI[sha256sum] = "8f1e2b2aade4dbfde98d82366d61baef2f62e812530160d2e6d0a5bb24e40bc0"

S = "${WORKDIR}/zeromq-${PV}"

#Uncomment to choose polling system manually. valid values are kqueue, epoll, devpoll, poll or select
#EXTRA_OECONF += "--with-poller=kqueue"
#CFLAGS_append = " -O0"
#CXXFLAGS_append = " -O0"

inherit autotools ptest pkgconfig

do_compile_ptest () {
    echo 'buildtest-TESTS: $(check_PROGRAMS)' >> ${B}/Makefile
    oe_runmake buildtest-TESTS
}

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests
    install -m 0755 ${B}/.libs/test_* ${D}${PTEST_PATH}/tests
}
