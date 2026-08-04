SUMMARY = "libdeflate is a library for fast, whole-buffer DEFLATE-based compression and decompression."
HOMEPAGE = "https://github.com/ebiggers/libdeflate"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://COPYING;md5=89f8c510215440fe2f208fba4afe07e0"

DEPENDS += "gzip zlib"

SRC_URI = "git://github.com/ebiggers/libdeflate.git;protocol=https;branch=master"

SRCREV = "c8c56a20f8f621e6a966b716b31f1dedab6a41e3"

inherit cmake pkgconfig
