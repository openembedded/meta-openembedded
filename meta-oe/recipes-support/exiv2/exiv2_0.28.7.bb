SUMMARY = "Exif, Iptc and XMP metadata manipulation library and tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

DEPENDS = "zlib expat brotli libinih"

SRC_URI = "git://github.com/Exiv2/exiv2.git;protocol=https;branch=0.28.x;tag=v${PV} \
           file://CVE-2026-25884-1.patch \
           file://CVE-2026-25884-2.patch \
           file://CVE-2026-27596-1.patch \
           file://CVE-2026-27596-2.patch \
           "
SRCREV = "afcb7a8ba84a7de36d2f1ee7689394e078697956"

PATCHTOOL = "git"

inherit cmake gettext
