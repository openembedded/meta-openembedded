DESCRIPTION = "C++ bindings for ZeroMQ"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db174eaf7b55a34a7c89551197f66e94"
DEPENDS = "zeromq"
SRCREV = "a88bf3e0b0bc6ed5f5b25a58f8997a1dae374c8b"

SRC_URI = "git://github.com/zeromq/cppzmq.git"

S = "${WORKDIR}/git"

do_install () {
        install -d ${D}/usr/include
        install -m 0755 ${S}/zmq.hpp ${D}/usr/include/
}

PACKAGES = "${PN}-dev"

RDEPENDS_${PN}-dev = "zeromq-dev"
