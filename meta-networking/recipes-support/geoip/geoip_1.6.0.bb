SUMMARY = "C library for country/city/organization to IP address or hostname mapping"
DESCRIPTION = "GeoIP is a C library that enables the user to find the country that any IP \
address or hostname originates from. It uses a file based database that is \
accurate as of March 2003. This database simply contains IP blocks as keys, and \
countries as values. This database should be more complete and accurate than \
using reverse DNS lookups."

HOMEPAGE = "http://dev.maxmind.com/geoip/"
SECTION = "Development/Libraries"

SRC_URI = "http://www.maxmind.com/download/geoip/api/c/GeoIP-1.6.0.tar.gz;name=tarball \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz;apply=no;name=GeoIP-dat \
           http://geolite.maxmind.com/download/geoip/database/GeoIPv6.dat.gz;apply=no;name=GeoIPv6-dat \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz;apply=no;name=GeoLiteCity-dat \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6-beta/GeoLiteCityv6.dat.gz;apply=no;name=GeoLiteCityv6-dat \
"

SRC_URI[tarball.md5sum] = "89f4cdfdab43f1d67364cd7c85bbe8ca"
SRC_URI[tarball.sha256sum] = "075a0c2815cd099e9ec35c9569db716a3fefcdbb6a10dbfa1ce7c6cd48d4a635"

SRC_URI[GeoIP-dat.md5sum] = "37c84ead332dda0362a5ac7b049b72d4"
SRC_URI[GeoIP-dat.sha256sum] = "79ff1099e96c2dc1c2539c9a18aaa13a9afd085cae477df60d95f1644d42bc07"

SRC_URI[GeoIPv6-dat.md5sum] = "e75b84a4044e81d6d4484e33816bc762"
SRC_URI[GeoIPv6-dat.sha256sum] = "a009b0f21968d2868e6dd19d14f3c3b8cd60ae84a4bfc2970df34d771a04811e"

SRC_URI[GeoLiteCity-dat.md5sum] = "4b6588d0bfe1af22e267ac90aa97f769"
SRC_URI[GeoLiteCity-dat.sha256sum] = "8a6467033a528f68b1a97de24d9d0ce86c8e8e83683820e16e433ddbd3f712f7"

SRC_URI[GeoLiteCityv6-dat.md5sum] = "ad0cb42518af7f752499425dca0952bb"
SRC_URI[GeoLiteCityv6-dat.sha256sum] = "eda67f4204ba9fa5204a53cdb629167cca9394c712f5378bc723a8c29c0b440f"

LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=d5d53d6b948c064f4070183180a4fa89 \
                    file://LICENSE;md5=f04db71812ba70aaba8b38da91984cd2 "

S = "${WORKDIR}/GeoIP-1.6.0"

inherit autotools

EXTRA_OECONF = "--disable-static               \
                --disable-dependency-tracking  "

do_install() {
    make DESTDIR=${D} install
    install -d ${D}/${datadir}/GeoIP
    install ${WORKDIR}/GeoIP.dat ${D}/${datadir}/GeoIP/
    install ${WORKDIR}/GeoIPv6.dat ${D}/${datadir}/GeoIP/
    install ${WORKDIR}/GeoLiteCity.dat ${D}/${datadir}/GeoIP/
    install ${WORKDIR}/GeoLiteCityv6.dat ${D}/${datadir}/GeoIP/
}

PACKAGES =+ "${PN}-database"
FILES_${PN}-database = ""
FILES_${PN}-database += "${datadir}/GeoIP/*"

