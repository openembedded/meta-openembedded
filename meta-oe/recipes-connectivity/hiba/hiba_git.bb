SUMMARY = "Google Host Identity Based Authorization"
DESCRIPTION = "HIBA is a system built on top of regular OpenSSH \
  certificate-based authentication that allows to manage flexible \
  authorization of principals on pools of target hosts without the need to \
  push customized authorized_users files periodically."

S = "${WORKDIR}/git/hiba"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=7e161abe9a4585310725dd86c28d1ae2"

PV = "0.0.0+git${SRCPV}"

inherit autotools

DEPENDS += "openssl zlib"

SRC_URI = " \
  git://github.com/google/hiba;protocol=https;branch=main;destsuffix=git/hiba;name=hiba \
  git://github.com/openssh/openssh-portable.git;protocol=https;branch=master;destsuffix=git/openssh-portable;name=openssh \
"
SRCREV_hiba = "84b8c4de84078c7a9d4213c2d9553ad9f66661cc"

# Internal OpenSSH version for HIBA to link against (the version doesn't have
#   to match the actual installed version).
# Discussion in
# https://lists.openembedded.org/g/openembedded-core/topic/94475279#172977
SRCREV_openssh = "15a01cf15f396f87c6d221c5a6af98331c818962"
SRCREV_FORMAT = "hiba"

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
