require recipes-graphics/xorg-driver/xorg-driver-video.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=e7f3e39474aeea5af381a8e103dafc36"

SUMMARY = "X.org server -- Geode GX2/LX display driver"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "7dafd19e274c771539b6d4d459423ccf"
SRC_URI[sha256sum] = "00db3bd697b32286eb0e42f5dbd7fcc8d7255dd11c299b1ee16a06a244868ff0"

SRC_URI += "file://xf86SetModeDefaultName.patch"

COMPATIBLE_HOST = "i.86.*-linux"

RDEPENDS_${PN} += "xserver-xorg-module-exa"
