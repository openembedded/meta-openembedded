DESCRIPTION = "The X Window System -- install this task to get a client/server based display multiplexer."
SECTION = "x11/server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PV = "1.0"
PR = "r7"

# WORK IN PROGRESS

inherit task

PACKAGES += "\
  ${PN}-server \
  ${PN}-utils \
"

RRECOMMENDS_${PN} = "\
  ${PN}-server \
  ${PN}-utils \
"

# Some machines don't set a *runtime* provider for X, so default to Xfbdev here
# virtual/xserver won't work, since the kdrive recipes will build multiple xserver packages
XSERVER ?= "xserver-kdrive-fbdev"
XSERVER_COMMON ?= "xserver-kdrive-common"
XSERVER_COMMON_shr = "xserver-common"

# This is also the reason why we have to make this package machine specific :/
PACKAGE_ARCH_${PN}-server = "${MACHINE_ARCH}"

RDEPENDS_${PN}-server = "\
  ${XSERVER} \
"

RDEPENDS_${PN}-utils = "\
  ${XSERVER_COMMON} \
  xserver-nodm-init \
  xauth \
  xhost \
  xset \
  xrandr \
"

