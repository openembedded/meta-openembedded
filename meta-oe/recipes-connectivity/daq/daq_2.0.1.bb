DESCRIPTION = "The dump DAQ test the various inline mode features "
HOMEPAGE = "http://www.snort.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f9ce51a65dd738dc1ae631d8b21c40e0"

PARALLEL_MAKE = ""

DEPENDS = "libpcap libpcre"

SRC_URI = "http://fossies.org/linux/misc/daq-${PV}.tar.gz \
            file://disable-run-test-program-while-cross-compiling.patch "

SRC_URI[md5sum] = "044aa3663d44580d005293eeb8ccf175"
SRC_URI[sha256sum] = "ebba87c2ec76ac65d2980934423b0597909caead3a86ce5d1ba1ea6ce518ec6d"

# these 2 create undeclared dependency on libdnet and libnetfilter-queue from meta-networking
# this error from test-dependencies script:
# daq/daq/latest lost dependency on  libdnet libmnl libnetfilter-queue libnfnetlink
EXTRA_OECONF += "--disable-nfq-module --disable-ipq-module"

inherit autotools
