require libx11.inc

LICENSE = "MIT & MIT-style & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=172255dee66bb0151435b2d5d709fcf7"

#--without-xcb is not an option anymore
#http://cgit.freedesktop.org/xorg/lib/libX11/commit/?id=15e5eaf62897b3179d1fbe457cb19f886f0449f8
DEPENDS_virtclass-native = "libxcb-native ${COMMON_DEPENDS}"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "4603bdbce1bd73cbc140de402fe6ed24"
SRC_URI[sha256sum] = "70f4e0f798645a0f269f362bfdbd4c7934dae3a2dd9ecbad28d6ede414f63ce2"
