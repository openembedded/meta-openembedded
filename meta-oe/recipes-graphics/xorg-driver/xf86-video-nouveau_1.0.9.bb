require recipes-graphics/xorg-driver/xorg-driver-video.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4641deddaa80fe7ca88e944e1fd94a94"

SUMMARY = "X.Org X server -- nouveau video driver"

DESCRIPTION = "Open-source X.org graphics driver for NVIDIA graphics"

DEPENDS += "virtual/libx11 libxvmc drm xf86driproto glproto \
            virtual/libgl xineramaproto libpciaccess"
RDEPENDS_${PN} += "xserver-xorg-module-exa"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

SRC_URI[md5sum] = "8b2c0df5de3929597ade8c5ddb489a44"
SRC_URI[sha256sum] = "b247c800e532fad1c80a5666d8ca0d4e5712064b6d7a3b030b32206a8de04482"
