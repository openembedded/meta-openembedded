SUMMARY = "Microsoft project for cloud-based client-server communication in native code using a modern asynchronous C++ API design."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/cpprest-lts/cpprestsdk/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/license.txt;md5=a2e15b954769218ff912468eecd6a02f"
DEPENDS = "openssl websocketpp zlib boost brotli"

EXTRA_OECMAKE = "-DCPPREST_EXPORT_DIR=cmake/cpprestsdk -DCPPREST_EXCLUDE_BROTLI=OFF -DWERROR=OFF"

SRC_URI = "git://github.com/cpprest-lts/cpprestsdk.git;protocol=https;branch=cpprest-lts"

# tag 2.10.21+cpprest-lts
SRCREV = "53a4b06ac33b67703ca62305dcb36083f93bbd1c"

inherit cmake pkgconfig
