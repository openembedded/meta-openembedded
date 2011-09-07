DESCRIPTION = "libdvdcss is a simple library designed for accessing DVDs like a block device without having to bother about the decryption."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
PR = "r1"

SRC_URI = "http://download.videolan.org/pub/libdvdcss/${PV}/libdvdcss-${PV}.tar.bz2"

inherit autotools

EXTRA_OECONF = " --disable-doc "

SRC_URI[md5sum] = "ebd5370b79ac5a83e5c61b24a214cf74"
SRC_URI[sha256sum] = "2394a75013ab66b592d8c91d53529ede392bc9faee37fe279bd29b455eff2220"
