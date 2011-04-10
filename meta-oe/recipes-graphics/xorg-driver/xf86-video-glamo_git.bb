require xorg-driver-video.inc
DESCRIPTION = "X.Org X server -- Glamo display driver with KMS support"
DEPENDS += "libdrm"

LIC_FILES_CHKSUM = "file://COPYING;md5=d8cbd99fff773f92e844948f74ef0df8"

RDEPENDS_${PN} = "xserver-xorg-extension-dri xserver-xorg-extension-dri2 xserver-xorg-extension-glx mesa-dri"
PE = "2"
PV = "1.0.0+gitr${SRCPV}"
PR = "${INC_PR}.5"

SRC_URI = "git://git.openmoko.org/git/xf86-video-glamo.git;protocol=git;branch=master \
           file://0001-glamo-drm-define-GLAMO_CMDQ_MAX_COUNT-instead-of-mag.patch \
          "

S = "${WORKDIR}/git"
SRCREV = "16af3c00195adc68cbd508e3613be4b2349260b3"

EXTRA_OECONF = " --enable-kms "
