require recipes-graphics/xorg-driver/xorg-driver-video.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=f8ad89d9d9a024f19d7dcc5acb044a42"

DESCRIPTION = "X.org server -- Geode GX2/LX display driver"
PR = "${INC_PR}.0"

SRC_URI[md5sum] = "a99c521db731c2f1df309057a8465e4c"
SRC_URI[sha256sum] = "28d845d727d99bfa4d4b93c5486ec01b72eb222e0910a766089db1103b1c2d92"

COMPATIBLE_HOST = "i.86.*-linux"
