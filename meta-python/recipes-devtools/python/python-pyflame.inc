SUMMARY = "Pyflame: A Ptracing Profiler For Python"
HOMEPAGE = "https://github.com/uber/pyflame"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"
DEPENDS = "python"

SRC_URI = "git://github.com/uber/pyflame.git;protocol=https;tag=v${PV}"
S = "${WORKDIR}/git"

inherit pkgconfig autotools

