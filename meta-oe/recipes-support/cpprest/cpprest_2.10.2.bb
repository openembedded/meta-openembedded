SUMMARY = "Microsoft project for cloud-based client-server communication in native code using a modern asynchronous C++ API design."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/Microsoft/cpprestsdk/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/../license.txt;md5=a2e15b954769218ff912468eecd6a02f"
DEPENDS = "openssl websocketpp zlib boost"

SRC_URI = "git://github.com/Microsoft/cpprestsdk.git;protocol=https;branch=master \
           file://fix-cmake-install.patch \
           file://0001-Fix-a-build-problem-on-Clang.patch;patchdir=.. \
           file://0002-Define-virtual-destructor.patch;patchdir=.. \
           file://0001-disable-more-Werror-warnings.patch;patchdir=.. \
           "

# tag 2.10.2
SRCREV= "fea848e2a77563cf2a6f28f8eab396fd6e787fbf"

S = "${WORKDIR}/git/Release"

inherit cmake
