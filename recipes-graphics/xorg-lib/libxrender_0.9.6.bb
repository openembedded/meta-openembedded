DESCRIPTION = "X11 Rendering Extension client library"

require xorg-lib-common.inc

LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=0db75cc842842b36f097fdae571b4b70"

DEPENDS += "virtual/libx11 renderproto xproto xdmcp"

PR = "r0"
PE = "1"

XORG_PN = "libXrender"


SRC_URI[md5sum] = "3b3b7d076c2384b6c600c0b5f4ba971f"
SRC_URI[sha256sum] = "7f58b1e263109e0a873eef8423aa14733a5499befbe645053aa622ed1f3ea668"
