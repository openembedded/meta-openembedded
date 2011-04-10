DESCRIPTION = "tangoGPS is a map and GPS application using Openstreetmap"
AUTHOR = "Marcus Bauer <marcus.bauer@gmail.com>"
HOMEPAGE = "http://tangogps.org/"
SECTION = "x11/applications"
LICENSE = "GPLv2"
PRIORITY = "optional"
DEPENDS = "curl gtk+ gconf sqlite3 libexif libsoup-2.4"

SRC_URI = "http://www.tangogps.org/downloads/tangogps-${PV}.tar.gz;name=archive"
S = "${WORKDIR}/tangogps-${PV}"

inherit autotools pkgconfig

RRECOMMENDS_${PN} = "gpsd"
RRECOMMENDS_shr = "fso-gpsd"
