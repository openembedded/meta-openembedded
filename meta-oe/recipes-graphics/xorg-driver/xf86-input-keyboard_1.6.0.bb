require xorg-driver-input.inc

SUMMARY = "X.Org X server -- keyboard input driver"

DESCRIPTION = "keyboard is an Xorg input driver for keyboards. The \
driver supports the standard OS-provided keyboard interface.  The driver \
functions as a keyboard input device, and may be used as the X server's \
core keyboard."

LIC_FILES_CHKSUM = "file://COPYING;md5=ea2099d24ac9e316a6d4b9f20b3d4e10"

DEPENDS += " kbproto"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "e2abe9f13e526a73cb68a7d257546eba"
SRC_URI[sha256sum] = "c46c790fec905d696573b7a374b10ab8b4389112a8f69993fe011006c99e858e"
