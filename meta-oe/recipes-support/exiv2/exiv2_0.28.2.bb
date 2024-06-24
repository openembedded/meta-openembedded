SUMMARY = "Exif, Iptc and XMP metadata manipulation library and tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

DEPENDS = "zlib expat brotli libinih"

SRC_URI = "git://github.com/Exiv2/exiv2.git;protocol=https;branch=0.28.x"
SRCREV = "04207b9c39bf7b3b1a7144f7ed4e4f16b4f29ef6"
S = "${WORKDIR}/git"

inherit cmake gettext
