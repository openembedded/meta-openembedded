DESCRIPTION = "X11 miscellaneous extension library"

require xorg-lib-common.inc

LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=0542b0321c1c9a7a20b23a1b9fa45f91"

DEPENDS += "xproto virtual/libx11 xextproto libxau libxdmcp"
PROVIDES = "xext"

PR = "r0"
PE = "1"

XORG_PN = "libXext"


SRC_URI[md5sum] = "9bb236ff0193e9fc1c1fb504dd840331"
SRC_URI[sha256sum] = "4aed3e211e41c47908c293515580e731c26048f61a1212bf0888d1f456de6ff7"
