HOMEPAGE = "https://github.com/embear-engineering/drm-framebuffer"
SUMMARY = "A simple application which can be used to test a Linux DRM device"
SECTION = "graphics"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7702f203b58979ebbc31bfaeb44f219c"

SRC_URI = "git://github.com/embear-engineering/drm-framebuffer;protocol=https;branch=main"

SRCREV = "343b33e3ca20345fb28e2e68930271b61c490ab2"

inherit cmake pkgconfig

DEPENDS = "libdrm"

