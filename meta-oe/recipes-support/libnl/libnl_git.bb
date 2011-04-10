require libnl.inc

PE = "1"
PV = "1.9+gitr${SRCPV}"
PR = "${INC_PR}.0"

DEPENDS = "flex-native bison-native"

S = "${WORKDIR}/git"
SRCREV = "d378220c96c3c8b6f27dca33e7d8ba03318f9c2d"
SRC_URI = "\
  git://git.kernel.org/pub/scm/libs/netlink/libnl.git;protocol=git \
  file://fix-pc-file.patch \
"

PACKAGES =+ "${PN}-route ${PN}-nf ${PN}-genl ${PN}-cli"
FILES_${PN}-route = "${libdir}/libnl-route.so.*"
FILES_${PN}-nf    = "${libdir}/libnl-nf.so.*"
FILES_${PN}-genl  = "${libdir}/libnl-genl.so.*"
FILES_${PN}-cli   = "${libdir}/libnl-cli.so.*"
