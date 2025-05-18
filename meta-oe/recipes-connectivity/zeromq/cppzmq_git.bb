DESCRIPTION = "C++ bindings for ZeroMQ"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db174eaf7b55a34a7c89551197f66e94"
DEPENDS = "zeromq"

SRCREV = "d67b6352b87a238775cd17e4376b980d07fa7939"
PV = "4.9.0"

SRC_URI = "git://github.com/zeromq/cppzmq.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DCPPZMQ_BUILD_TESTS=OFF"

PACKAGES = "${PN}-dev"

RDEPENDS:${PN}-dev = "zeromq-dev"
DEV_PKG_DEPENDENCY = ""
