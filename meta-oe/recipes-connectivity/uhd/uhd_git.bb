require uhd.inc

PV = "3.5.0"
PR = "${INC_PR}.0"

SRC_URI = "git://ettus.sourcerepo.com/ettus/uhd.git;branch=maint;protocol=git \
          "
S = "${WORKDIR}/git/host"

SRCREV = "5cb9779da40491b06157596b06422f704a58c7d0"
