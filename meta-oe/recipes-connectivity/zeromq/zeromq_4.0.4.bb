DESCRIPTION = "ZeroMQ looks like an embeddable networking library but acts like a concurrency framework"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=d5311495d952062e0e4fbba39cbf3de1"

SRC_URI = "http://download.zeromq.org/zeromq-${PV}.tar.gz \
           file://run-ptest \
           "
SRC_URI[md5sum] = "f3c3defbb5ef6cc000ca65e529fdab3b"
SRC_URI[sha256sum] = "1ef71d46e94f33e27dd5a1661ed626cd39be4d2d6967792a275040e34457d399"

S = "${WORKDIR}/zeromq-${PV}"

#Uncomment to choose polling system manually. valid values are kqueue, epoll, devpoll, poll or select
#EXTRA_OECONF += "--with-poller=kqueue"
#CFLAGS_append += "-O0"
#CXXFLAGS_append += "-O0"

inherit autotools ptest

do_install_ptest () {
        install -d ${D}${PTEST_PATH}/tests
        install -m 0755 ${B}/tests/.libs/* ${D}${PTEST_PATH}/tests
}
