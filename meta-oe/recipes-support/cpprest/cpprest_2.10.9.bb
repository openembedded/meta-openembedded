SUMMARY = "Microsoft project for cloud-based client-server communication in native code using a modern asynchronous C++ API design."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/Microsoft/cpprestsdk/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/license.txt;md5=a2e15b954769218ff912468eecd6a02f"
DEPENDS = "openssl websocketpp zlib boost brotli"

EXTRA_OECMAKE = "-DCPPREST_EXPORT_DIR=cmake -DCPPREST_EXCLUDE_BROTLI=OFF"

SRC_URI = "git://github.com/Microsoft/cpprestsdk.git;protocol=https;branch=master \
           "

# tag 2.10.9
SRCREV= "8f0393dd7be08f2d9b44cc09b8b97f9bafb109a5"

S = "${WORKDIR}/git"

inherit cmake pkgconfig
