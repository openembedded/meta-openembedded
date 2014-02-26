DESCRIPTION = "libdvdcss is a simple library designed for accessing DVDs like a block device without having to bother about the decryption."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://download.videolan.org/pub/libdvdcss/${PV}/libdvdcss-${PV}.tar.bz2"

inherit autotools

EXTRA_OECONF = " --disable-doc "

SRC_URI[md5sum] = "53cfc52a60a156763c425572e5179273"
SRC_URI[sha256sum] = "84f1bba6cfef1df87f774fceaefc8e73c4cda32e8f6700b224ad0acb5511ba2c"
