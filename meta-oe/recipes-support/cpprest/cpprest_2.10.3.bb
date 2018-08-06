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
           file://fix-cmake-install.patch \
           file://747.patch \
           file://732.patch \
           "

# tag 2.10.3
SRCREV= "e388a2e523f4d0b6aee2bb923637d82d8b969556"

S = "${WORKDIR}/git"

inherit cmake
