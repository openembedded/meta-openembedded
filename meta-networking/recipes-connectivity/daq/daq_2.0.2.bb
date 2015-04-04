SUMMARY = "The dump DAQ test the various inline mode features "
HOMEPAGE = "http://www.snort.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f9ce51a65dd738dc1ae631d8b21c40e0"

PARALLEL_MAKE = ""

DEPENDS = "libpcap libpcre libdnet"

SRC_URI = "http://fossies.org/linux/misc/daq-${PV}.tar.gz \
            file://disable-run-test-program-while-cross-compiling.patch "

# these 2 create undeclared dependency on libdnet and libnetfilter-queue from meta-networking
# this error from test-dependencies script:
# daq/daq/latest lost dependency on  libdnet libmnl libnetfilter-queue libnfnetlink
#
# never look to /usr/local lib while cross compiling

EXTRA_OECONF = "--disable-nfq-module --disable-ipq-module --includedir=${includedir} \
	--with-libpcap-includes=${STAGING_INCDIR} --with-dnet-includes=${STAGING_LIBDIR}"

SRC_URI[md5sum] = "865bf9b750a2a2ca632591a3c70b0ea0"
SRC_URI[sha256sum] = "d65d1e67c4994e02c3142c49a648642e780b7e3d942b4a51f605309beac269a8"

inherit autotools
