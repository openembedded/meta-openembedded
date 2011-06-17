require uhd.inc

PR = "${INC_PR}.4"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;protocol=git \
           file://0001-HACK-work-around-Wl-as-needed-problems.patch"
S = "${WORKDIR}/git/host"

SRCREV = "0aff497dacc9cc4eba5d800cc46343da083cfdf1"
