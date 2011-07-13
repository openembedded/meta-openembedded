DESCRIPTION = "FoxtrotGPS is a map and GPS application using OpenStreetMap"
AUTHOR = "Joshua Judson Rosen <rozzin@geekspace.com>"
HOMEPAGE = "http://www.foxtrotgps.org/"
SECTION = "x11/applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "curl gtk+ gconf libglade sqlite3 libexif gpsd"

SRC_URI = "http://www.foxtrotgps.org/releases/${P}.tar.gz"
SRC_URI[md5sum] = "982d14cdb0439cc4d6aa6cefc232124d"
SRC_URI[sha256sum] = "059158fd8e61137e2e6d6e01685add7fb85cce8483faa99efb01187f2ba50841"

inherit autotools pkgconfig

RRECOMMENDS_${PN} = "gpsd"
