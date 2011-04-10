require xorg-util-common.inc

DESCRIPTION = "X autotools macros"
PE = "1"

LICENSE = "MIT & MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=1970511fddd439b07a6ba789d28ff662"

ALLOW_EMPTY = "1"
BBCLASSEXTEND = "native nativesdk"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "31e9ddcbc1d8bc8c09ab180443974dd1"
SRC_URI[sha256sum] = "7bff944fb120192e7fe1706e9c0b7e41666e7983ce3e2bdef0b7734392d9e695"
