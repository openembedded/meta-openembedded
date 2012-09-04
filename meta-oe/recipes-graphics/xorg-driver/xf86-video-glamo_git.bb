require recipes-graphics/xorg-driver/xorg-driver-video.inc
DESCRIPTION = "X.Org X server -- Glamo display driver with KMS support"
DEPENDS += "libdrm"
RDEPENDS_${PN} += "xserver-xorg-module-exa"

LIC_FILES_CHKSUM = "file://COPYING;md5=d8cbd99fff773f92e844948f74ef0df8"

PE = "2"
PV = "1.0.0+gitr${SRCPV}"
PR = "${INC_PR}.2"

SRC_URI = "git://git.openmoko.org/git/xf86-video-glamo.git;protocol=git;branch=master \
           file://0001-glamo-kms-driver-drop-unused-xf86_config.patch \
           file://0001-fix-build-with-KMS-disabled.patch \
          "

S = "${WORKDIR}/git"
SRCREV = "cb9ed17035a79e49dde26a1e7e2bc5f10fd8144b"

EXTRA_OECONF = " --disable-kms "
