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
           file://845.patch \
           "

# tag 2.10.6
SRCREV= "66e50f02dde92f802bbd3a8d79c6352954665b9b"

S = "${WORKDIR}/git"

inherit cmake
