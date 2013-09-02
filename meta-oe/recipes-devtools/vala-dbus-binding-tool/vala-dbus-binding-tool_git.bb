require vala-dbus-binding-tool.inc

DEFAULT_PREFERENCE = "-1"

SRCREV = "53d56816a4154e3467f07bd22f4e27d166e3e3f3"
PV = "0.4.0+gitr${SRCPV}"

SRC_URI = "${FREESMARTPHONE_GIT}/vala-dbus-binding-tool.git;branch=master"
S = "${WORKDIR}/git"
