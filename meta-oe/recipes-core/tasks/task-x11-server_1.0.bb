DESCRIPTION = "Task for an image with Xserver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
PR = "r9"

inherit task

# for backwards compatibility
RPROVIDES_${PN} += "task-xserver"

XSERVER ?= "xserver-xorg \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-video-fbdev \
           xf86-input-keyboard \
"


RDEPENDS_${PN} = " \
  ${XSERVER} \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
