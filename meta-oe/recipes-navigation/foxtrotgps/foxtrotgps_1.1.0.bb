DESCRIPTION = "FoxtrotGPS is a map and GPS application using OpenStreetMap"
AUTHOR = "Joshua Judson Rosen <rozzin@geekspace.com>"
HOMEPAGE = "http://www.foxtrotgps.org/"
SECTION = "x11/applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "curl gtk+ gconf libglade sqlite3 libexif gpsd"

SRC_URI = "http://www.foxtrotgps.org/releases/${P}.tar.gz"
SRC_URI[md5sum] = "1585d332ae546a0f84e8327ac21b27b9"
SRC_URI[sha256sum] = "2d0d66d3e6d3c6ca1c8e5d82129182a49691b9622067b7c5edf82d808ebf1a77"

inherit autotools pkgconfig perlnative

RRECOMMENDS_${PN} = "gpsd"
