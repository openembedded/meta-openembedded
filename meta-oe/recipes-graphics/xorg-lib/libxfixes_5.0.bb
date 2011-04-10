SUMMARY = "XFixes: X Fixes extension library."

DESCRIPTION = "X applications have often needed to work around various \
shortcomings in the core X window system.  This extension is designed to \
provide the minimal server-side support necessary to eliminate problems \
caused by these workarounds."

require xorg-lib-common.inc

LICENSE = "MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c1ce42c334a6f5cccb0277556a053e0"

DEPENDS += "virtual/libx11 xproto fixesproto xextproto"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "678071bd7f9f7467e2fc712d81022318"
SRC_URI[sha256sum] = "537a2446129242737a35db40081be4bbcc126e56c03bf5f2b142b10a79cda2e3"

BBCLASSEXTEND = "native"

XORG_PN = "libXfixes"
