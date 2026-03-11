SUMMARY = "Exif, Iptc and XMP metadata manipulation library and tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

DEPENDS = "zlib expat brotli libinih"

SRC_URI = "git://github.com/Exiv2/exiv2.git;protocol=https;branch=0.28.x \
           file://0001-Revert-fix-copy-constructors.patch \
           file://0001-CVE-2025-54080-fix.patch \
           file://0001-Add-new-method-appendIccProfile-to-fix-quadratic-per.patch \
           file://CVE-2026-25884-1.patch \
           file://CVE-2026-25884-2.patch \
           file://CVE-2026-27596-1.patch \
           file://CVE-2026-27596-2.patch \
           file://CVE-2026-27631-1.patch \
           file://CVE-2026-27631-2.patch \
           "
SRCREV = "a6a79ef064f131ffd03c110acce2d3edb84ffa2e"
S = "${WORKDIR}/git"

PATCHTOOL = "git"

inherit cmake gettext
