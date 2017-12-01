SUMMARY = "An open source remote desktop protocol(rdp) server."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=72cfbe4e7bd33a0a1de9630c91195c21 \
"

inherit distro_features_check

DEPENDS = "openssl virtual/libx11 libxfixes libxrandr"

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "git://github.com/neutrinolabs/xrdp.git"

SRCREV = "c295dd61b882e8b56677cf12791f43634f9190b5"

PV = "0.9.4+git${SRCPV}"

S = "${WORKDIR}/git"
