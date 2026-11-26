SUMMARY = "A header only library for creating and validating json web tokens in c++"
HOMEPAGE = "https://thalhammer.github.io/jwt-cpp/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8325a5ce4414c65ffdda392e0d96a9ff"

SRC_URI = "git://github.com/Thalhammer/jwt-cpp.git;branch=master;protocol=https;tag=v${PV} \
            "

SRCREV = "f573f993c9a85cd919a430a5a7a2b7bbb9036a93"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

DEPENDS = "openssl"

inherit cmake

EXTRA_OECMAKE += "-DJWT_BUILD_EXAMPLES=OFF -DJWT_CMAKE_FILES_INSTALL_DIR=${libdir}/cmake/${BPN}"

BBCLASSEXTEND = "native nativesdk"
