require uhd.inc

PV = "3.2.1"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;protocol=git \
           file://0001-HACK-work-around-Wl-as-needed-problems.patch;striplevel=2"
S = "${WORKDIR}/git/host"

SRCREV = "fc349d30507620baca935e75e68a9554b40d427c"
