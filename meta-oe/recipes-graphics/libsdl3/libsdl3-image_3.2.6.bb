SUMMARY = "Simple DirectMedia Layer image library v2"
SECTION = "libs"

LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fbb0010b2f7cf6e8a13bcac1ef4d2455"

DEPENDS = "tiff zlib libpng jpeg libsdl3 libwebp"

SRC_URI = "git://github.com/libsdl-org/SDL_image.git;protocol=https;branch=release-3.2.x"
SRCREV = "d78dd65454f442bfdbc373201165f1ef579484ab"


inherit cmake pkgconfig

FILES:${PN} += "${datadir}/licenses"
