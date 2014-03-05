SUMMARY = "The dump DAQ test the various inline mode features "
HOMEPAGE = "http://www.snort.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f9ce51a65dd738dc1ae631d8b21c40e0"

PARALLEL_MAKE = ""

DEPENDS = "libpcap libpcre "

SRC_URI = "http://fossies.org/linux/misc/daq-${PV}.tar.gz \
            file://disable-run-test-program-while-cross-compiling.patch "

SRC_URI[md5sum] = "865bf9b750a2a2ca632591a3c70b0ea0"
SRC_URI[sha256sum] = "d65d1e67c4994e02c3142c49a648642e780b7e3d942b4a51f605309beac269a8"

inherit autotools
