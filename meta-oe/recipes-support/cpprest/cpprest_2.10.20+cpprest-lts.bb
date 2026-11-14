SUMMARY = "Microsoft project for cloud-based client-server communication in native code using a modern asynchronous C++ API design."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/cpprest-lts/cpprestsdk/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/license.txt;md5=a2e15b954769218ff912468eecd6a02f"
DEPENDS = "openssl websocketpp zlib boost brotli"

EXTRA_OECMAKE = "-DCPPREST_EXPORT_DIR=cmake/cpprestsdk -DCPPREST_EXCLUDE_BROTLI=OFF -DWERROR=OFF -DCPPREST_TEST_NETWORK=OFF"

SRC_URI = "git://github.com/cpprest-lts/cpprestsdk.git;protocol=https;branch=cpprest-lts \
           file://63.patch \
           file://64.patch \
           file://65.patch "

# tag 2.10.20+cpprest-lts
SRCREV = "feadb5b7d01a09d124d6d6c7bfeee648c15b37d1"

inherit cmake pkgconfig
