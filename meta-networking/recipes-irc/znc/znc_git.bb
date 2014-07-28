SUMMARY = "ZNC, an advanced IRC bouncer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "openssl"

PV = "1.0+git"

SRCREV = "ef59c23068547c132cb678092fba9a21317fd5f2"
SRC_URI = "git://github.com/znc/znc.git \
           file://0001-Fix-NULL-pointer-dereference-in-webadmin.patch \
          "

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

# ZNC has a custom autogen.sh that states that this command is needed *and* expected to fail
do_configure_prepend() {
    automake --add-missing || true
}
