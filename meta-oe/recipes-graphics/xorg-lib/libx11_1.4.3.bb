require libx11.inc

LICENSE = "MIT & MIT-style & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=172255dee66bb0151435b2d5d709fcf7"

#--without-xcb is not an option anymore
#http://cgit.freedesktop.org/xorg/lib/libX11/commit/?id=15e5eaf62897b3179d1fbe457cb19f886f0449f8
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "85e942627aaa020813e0eb8433353563"
SRC_URI[sha256sum] = "38b5ddd93291714a46a02cb8a5dd94b995a04ed76a608551c44d1598e113635a"
