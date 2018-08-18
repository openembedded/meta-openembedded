SUMMARY = "Microsoft project for cloud-based client-server communication in native code using a modern asynchronous C++ API design."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/Microsoft/cpprestsdk/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/license.txt;md5=a2e15b954769218ff912468eecd6a02f"
DEPENDS = "openssl websocketpp zlib boost"

SRC_URI = "git://github.com/Microsoft/cpprestsdk.git;protocol=https;branch=master \
           file://disable-outside-tests.patch \
           file://disable-test-timeouts.patch \
           file://disable-float-tests.patch \
           file://747.patch \
           file://845.patch \
           "

# tag 2.10.5
SRCREV= "25d6b26f7038eeec3b51e2e0a70960a8e34fcd68"

S = "${WORKDIR}/git"

inherit cmake
