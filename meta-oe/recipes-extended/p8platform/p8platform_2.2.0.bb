SUMMARY = "Platform support library used by libCEC and binary add-ons for Kodi"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=b3a719e97f49e4841e90573f9b1a98ac"

SRC_URI = "git://github.com/Pulse-Eight/platform.git;branch=master;protocol=https;tag=p8-platform-${PV}"
SRCREV = "939f0dddb02319b967caac4d3baac6b1b6f13fc1"
UPSTREAM_CHECK_GITTAGREGEX = "^p8-platform-(?P<pver>\d+(\.\d+)+)$"


inherit cmake pkgconfig

FILES:${PN}-dev += "${libdir}/p8-platform"

RDEPENDS:${PN}-dev = ""
