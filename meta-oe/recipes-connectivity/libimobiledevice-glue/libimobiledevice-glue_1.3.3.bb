SUMMARY = "Library with common code used by the libraries and tools around the libimobiledevice project"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=6ab17b41640564434dda85c06b7124f7 \
"

HOMEPAGE = "http://www.libimobiledevice.org/"

DEPENDS = "libplist"

SRCREV = "ecc0f1ba4fca96f3d7ef060b6c53f2e0bcfe829f"
SRC_URI = "git://github.com/libimobiledevice/libimobiledevice-glue;protocol=https;branch=master"

inherit autotools pkgconfig
