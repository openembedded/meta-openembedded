require uhd.inc

PV = "3.3.1"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;protocol=git \
           file://0001-HACK-work-around-Wl-as-needed-problems.patch;striplevel=2"
S = "${WORKDIR}/git/host"

SRCREV = "f8d66fcfb14062283cdb0d0cbe4f77e2964ceb82"
