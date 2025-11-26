SUMMARY = "Xorg drivers for xrdp."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a2523660329fdca3d954c0a87390e007"

inherit autotools pkgconfig 

DEPENDS = "virtual/libx11 xserver-xorg xrdp nasm-native"

inherit features_check
REQUIRED_DISTRO_FEATURES = "x11 pam"

SRC_URI = "git://github.com/neutrinolabs/xorgxrdp.git;branch=v0.10;protocol=https;tag=v${PV}"

SRCREV = "2b1f913f4aa6b88d63d9ce9302c61cb0e39483f6"

FILES:${PN} += "${libdir}/xorg/modules/*"
FILES:${PN}-staticdev += " \
    ${libdir}/xorg/modules/libxorgxrdp.a \
    ${libdir}/xorg/modules/drivers/xrdpdev_drv.a \
    ${libdir}/xorg/modules/input/xrdpmouse_drv.a \
    ${libdir}/xorg/modules/input/xrdpkeyb_drv.a \
    "

require recipes-graphics/xorg-xserver/xserver-abi.inc

python add_xorg_abi_depends() {
    _add_xorg_abi_depends(d, "xinput")
    _add_xorg_abi_depends(d, "videodrv")
}
PACKAGEFUNCS =+ "add_xorg_abi_depends"
