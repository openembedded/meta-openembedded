require uhd.inc

PV = "3.5.2"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;branch=maint;protocol=git \
"
S = "${WORKDIR}/git/host"

SRCREV = "0cce80c1ef2bcdb5094d579a6853261cebd2ec03"
