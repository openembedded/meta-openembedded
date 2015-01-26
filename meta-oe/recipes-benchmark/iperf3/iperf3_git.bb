SUMMARY = "Network benchmark tool"
DESCRIPTION = "\
iperf is a tool for active measurements of the maximum achievable bandwidth \
on IP networks. It supports tuning of various parameters related to timing, \
protocols, and buffers. For each test it reports the bandwidth, loss, and \
other parameters."
HOMEPAGE = "http://software.es.net/iperf/"
SECTION = "console/network"
BUGTRACKER = "https://github.com/esnet/iperf/issues"
AUTHOR = "ESNET <info@es.net>, Lawrence Berkeley National Laboratory <websupport@lbl.gov>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab59a0c3a4bc3954d1ece68ea19d77a4"

SRC_URI = "\
    git://github.com/esnet/iperf.git;branch=3.0-STABLE \
    file://automake-foreign.patch \
    file://fix-examples.patch \
"

PV = "3.0.10+gitr${SRCPV}"
SRCREV = "de420cc741dd8967ebc57f80b7712556442de81b"

S = "${WORKDIR}/git"

inherit autotools
BBCLASSEXTEND = "native"
