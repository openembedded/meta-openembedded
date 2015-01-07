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

SRC_URI[GeoIP-dat.md5sum] = "4bc1e8280fe2db0adc3fe48663b8926e"
SRC_URI[GeoIP-dat.sha256sum] = "7fd7e4829aaaae2677a7975eeecd170134195e5b7e6fc7d30bf3caf34db41bcd"

SRC_URI[GeoIPv6-dat.md5sum] = "aac7e6e9b141de80934ecee52daf7f56"
SRC_URI[GeoIPv6-dat.sha256sum] = "126fd2953eb193e60538e30b4465610530383f7a782745cacdca5ba6825f471c"

SRC_URI[GeoLiteCity-dat.md5sum] = "15a42c684c53d2309e6632a6d6e02531"
SRC_URI[GeoLiteCity-dat.sha256sum] = "5ec02a6d39d545c77ec12cc30c6a8856883d8f55522fc5cd4f25af80163c6b3c"

SRC_URI[GeoLiteCityv6-dat.md5sum] = "49d6ec946fa0a2575b5112a68d71f933"
SRC_URI[GeoLiteCityv6-dat.sha256sum] = "7a345e6cf0e59f8ab589ff15020241f0b03342dd04cc584f814c4f4700d49405"


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

