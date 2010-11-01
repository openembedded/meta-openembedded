require xorg-lib-common.inc
DESCRIPTION = "X11 Rendering Extension client library"
LICENSE = "BSD-X"
DEPENDS += "virtual/libx11 renderproto xproto libxdmcp"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "3b3b7d076c2384b6c600c0b5f4ba971f"
SRC_URI[archive.sha256sum] = "7f58b1e263109e0a873eef8423aa14733a5499befbe645053aa622ed1f3ea668"

BBCLASSEXTEND = "native nativesdk"

XORG_PN = "libXrender"
