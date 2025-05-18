SUMMARY = "A header only library for creating and validating json web tokens in c++"
HOMEPAGE = "https://thalhammer.github.io/jwt-cpp/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8325a5ce4414c65ffdda392e0d96a9ff"

SRC_URI = "git://github.com/Thalhammer/jwt-cpp.git;branch=master;protocol=https \
            "

SRCREV = "4a537e969891dde542ad8b1a4a214955a83be29f"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

inherit cmake

EXTRA_OECMAKE += "-DJWT_BUILD_EXAMPLES=OFF -DJWT_CMAKE_FILES_INSTALL_DIR=${libdir}/cmake"
