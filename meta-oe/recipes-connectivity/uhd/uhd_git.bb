require uhd.inc

PV = "3.5.3"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;branch=maint \
           file://0001-use-uint8_t-from-boost-namespace.patch;striplevel=2 \
          "
S = "${WORKDIR}/git/host"

SRCREV = "f4c58120dc1e3674ee0228213f0f0011a0f80dcf"
