require uhd.inc

PV = "3.4.1"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;protocol=git \
          "
S = "${WORKDIR}/git/host"

SRCREV = "3fb067567b0c799b3c707938491eb8489d85fefa"
