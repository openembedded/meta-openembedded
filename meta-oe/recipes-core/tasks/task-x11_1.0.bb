DESCRIPTION = "The X Window System -- install this task to get a client/server based display multiplexer."
SECTION = "x11/server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r10"

inherit task allarch

PACKAGES += "${PN}-utils"

RRECOMMENDS_${PN} = "\
  ${PN}-server \
  ${PN}-utils \
"

# xserver-common, x11-common
VIRTUAL-RUNTIME_xserver_common ?= "xserver-common"

# elsa, xserver-nodm-init or xserver-nodm-init-systemd
VIRTUAL-RUNTIME_graphical_init_manager ?= "xserver-nodm-init-systemd"

RDEPENDS_${PN}-utils = "\
  ${VIRTUAL-RUNTIME_xserver_common} \
  ${VIRTUAL-RUNTIME_graphical_init_manager} \
  xauth \
  xhost \
  xset \
  xrandr \
"

