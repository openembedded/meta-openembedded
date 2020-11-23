DESCRIPTION = "Heaptrack"
HOMEPAGE = "https://phabricator.kde.org/source/heaptrack/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "zlib boost libunwind elfutils"

SRC_URI = "git://github.com/KDE/heaptrack.git;protocol=https"

SRCREV = "bc9e3744bcc47de978673d1e382f4125a1ab5fa8"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DHEAPTRACK_BUILD_GUI=OFF"

BBCLASSEXTEND = "native nativesdk"
