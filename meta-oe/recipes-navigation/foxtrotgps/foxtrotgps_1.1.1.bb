DESCRIPTION = "FoxtrotGPS is a map and GPS application using OpenStreetMap"
AUTHOR = "Joshua Judson Rosen <rozzin@geekspace.com>"
HOMEPAGE = "http://www.foxtrotgps.org/"
SECTION = "x11/applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "curl gtk+ gconf libglade sqlite3 libexif gpsd"

SRC_URI = "http://www.foxtrotgps.org/releases/${P}.tar.gz"
SRC_URI[md5sum] = "6777d448ee9d3ba195f9d26ea90e3163"
SRC_URI[sha256sum] = "ae9706285510554cc0813ac92522e0d1672b0ddb065307bfacfcff3c328f6adb"

inherit autotools pkgconfig perlnative

do_configure_prepend() {
  mv ${S}/configure.in ${S}/configure.ac
}

RRECOMMENDS_${PN} = "gpsd"
