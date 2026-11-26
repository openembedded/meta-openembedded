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
# Internal OpenSSH version for HIBA to link against, matching the version
# carried in oe-core (10.6p1 / V_10_6_P1).
# Discussion in
# https://lists.openembedded.org/g/openembedded-core/topic/94475279#172977
SRCREV_openssh = "a8c72b842423f0774f542d3c2197247c3dd28d74"
SRCREV_FORMAT = "hiba"
PV = "1.4+git"

SRC_URI = " \
  git://github.com/google/hiba;protocol=https;branch=main;destsuffix=git/hiba;name=hiba \
  git://github.com/openssh/openssh-portable.git;protocol=https;branch=master;destsuffix=git/openssh-portable;name=openssh \
"

S = "${UNPACKDIR}/git/hiba"

inherit autotools

EXTRA_OECONF += " \
  --with-opensshdir=${UNPACKDIR}/git/openssh-portable \
"

autotools_preconfigure:append() {
  cd "${UNPACKDIR}/git/openssh-portable"
  autoreconf
  ./configure ${CONFIGUREOPTS}
  oe_runmake
  # OpenSSH sshkey.o references pkcs11_key_free and pkcs11_sign, which are
  # compiled into ssh-pkcs11.o but not packaged into OpenSSH's libssh.a.
  # Append ssh-pkcs11.o into libssh.a so HIBA can link against it.
  ${AR} rv libssh.a ssh-pkcs11.o
  ${RANLIB} libssh.a
  cd "${S}"
}

