require xorg-proto-common.inc
SUMMARY = "XExt: X Extension headers"

DESCRIPTION = "This package provides the wire protocol for several X \
extensions.  These protocol extensions include DOUBLE-BUFFER, DPMS, \
Extended-Visual-Information, LBX, MIT_SHM, MIT_SUNDRY-NONSTANDARD, \
Multi-Buffering, SECURITY, SHAPE, SYNC, TOG-CUP, XC-APPGROUP, XC-MISC, \
XTEST.  In addition a small set of utility functions are also \
available."

LICENSE = "MIT & MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=86f273291759d0ba2a22585cd1c06c53"

PE = "1"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "220732210ceffb01bf1caf970e3b1bfb"
SRC_URI[sha256sum] = "d2bc4208c6b1883ebe00bc5c0048e5d825038cda56775f74bb4aa89afdc576d5"

