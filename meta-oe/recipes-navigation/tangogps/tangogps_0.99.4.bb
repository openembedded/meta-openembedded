DESCRIPTION = "tangoGPS is a map and GPS application using Openstreetmap"
AUTHOR = "Marcus Bauer <marcus.bauer@gmail.com>"
HOMEPAGE = "http://tangogps.org/"
SECTION = "x11/applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "curl gtk+ gconf sqlite3 libexif libsoup-2.4 bluez4"
PR = "r1"

SRC_URI = "http://www.tangogps.org/downloads/${P}.tar.gz \
           file://remove.unused.header.patch"
SRC_URI[md5sum] = "0f07ede94a21eb84f5e017fa88a1fc3d"
SRC_URI[sha256sum] = "660fdf89ef3c379f2fc0c2a9d0c9d3bfa5345835786b72bf9f513ba9ec2c812a"

inherit autotools pkgconfig

RRECOMMENDS_${PN} = "gpsd"
