SUMMARY = "The ABI Generic Analysis and Instrumentation Library"
HOMEPAGE = "https://sourceware.org/libabigail/"

LICENSE = "Apache-2.0 WITH LLVM-exception"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0bcd48c3bdfef0c9d9fd17726e4b7dab"

SRC_URI = "http://mirrors.kernel.org/sourceware/${BPN}/${BP}.tar.xz \
           "
SRC_URI[sha256sum] = "d34ea7c4b150f61e22edb1d16157e12c43fd18f8e8f09abce053d82ccaf92e06"

DEPENDS = "elfutils libxml2 xxhash"
DEPENDS:append:libc-musl = " fts"

inherit autotools pkgconfig lib_package

BBCLASSEXTEND = "native nativesdk"
