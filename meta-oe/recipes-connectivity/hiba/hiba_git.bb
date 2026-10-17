SUMMARY = "Google Host Identity Based Authorization"
DESCRIPTION = "HIBA is a system built on top of regular OpenSSH \
  certificate-based authentication that allows to manage flexible \
  authorization of principals on pools of target hosts without the need to \
  push customized authorized_users files periodically."
HOMEPAGE = "https://github.com/google/hiba"
SECTION = "console/network"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=7e161abe9a4585310725dd86c28d1ae2"
DEPENDS += "openssl zlib"
SRCREV_hiba = "642d64ffec5719178644078867bcd4ff88298621"
# Internal OpenSSH version for HIBA to link against (the version doesn't have
#   to match the actual installed version).
# Discussion in
# https://lists.openembedded.org/g/openembedded-core/topic/94475279#172977
SRCREV_openssh = "15a01cf15f396f87c6d221c5a6af98331c818962"
SRCREV_FORMAT = "hiba"
PV = "1.4+git"

SRC_URI = " \
  git://github.com/google/hiba;protocol=https;branch=main;destsuffix=git/hiba;name=hiba \
  git://github.com/openssh/openssh-portable.git;protocol=https;branch=master;destsuffix=git/openssh-portable;name=openssh \
"

S = "${WORKDIR}/git/hiba"

inherit autotools

EXTRA_OECONF += " \
  --with-opensshdir=${S}/../openssh-portable \
"

autotools_preconfigure:append() {
  cd "${S}/../openssh-portable"
  autoreconf
  ./configure ${CONFIGUREOPTS}
  oe_runmake
  cd "${S}"
}
