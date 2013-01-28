require recipes-graphics/xorg-driver/xorg-driver-video.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=e7f3e39474aeea5af381a8e103dafc36"

DESCRIPTION = "X.org server -- Geode GX2/LX display driver"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "c9a4e1c5438240e5bdce332f92df9163"
SRC_URI[sha256sum] = "4c750b46c932934faa008919922f5ac8a572afcfe265ac8d7c203b8167a0cfcc"

COMPATIBLE_HOST = "i.86.*-linux"

RDEPENDS_${PN} += "xserver-xorg-module-exa"
