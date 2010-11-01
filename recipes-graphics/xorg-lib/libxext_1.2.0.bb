require xorg-lib-common.inc
DESCRIPTION = "X11 miscellaneous extension library"
DEPENDS += "xproto virtual/libx11 xextproto libxau"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "9bb236ff0193e9fc1c1fb504dd840331"
SRC_URI[archive.sha256sum] = "4aed3e211e41c47908c293515580e731c26048f61a1212bf0888d1f456de6ff7"

BBCLASSEXTEND = "native nativesdk"

XORG_PN = "libXext"
