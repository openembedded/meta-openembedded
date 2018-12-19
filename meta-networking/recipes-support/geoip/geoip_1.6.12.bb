SUMMARY = "C library for country/city/organization to IP address or hostname mapping"
DESCRIPTION = "GeoIP is a C library that enables the user to find the country that any IP \
address or hostname originates from. It uses a file based database that is \
accurate as of March 2003. This database simply contains IP blocks as keys, and \
countries as values. This database should be more complete and accurate than \
using reverse DNS lookups."

HOMEPAGE = "http://dev.maxmind.com/geoip/"
SECTION = "libdevel"

SRC_URI = "git://github.com/maxmind/geoip-api-c.git \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz;apply=no;name=GeoIP-dat;downloadfilename=GeoIP.dat.20181120.gz \
           http://geolite.maxmind.com/download/geoip/database/GeoIPv6.dat.gz;apply=no;name=GeoIPv6-dat;downloadfilename=GeoIPv6.dat.20181120.gz \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz;apply=no;name=GeoLiteCity-dat;downloadfilename=GeoLiteCity.dat.20181120.gz \
           http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6-beta/GeoLiteCityv6.dat.gz;apply=no;name=GeoLiteCityv6-dat;downloadfilename=GeoLiteCityv6.dat.20181120.gz \
           file://run-ptest \
"
SRCREV = "4b526e7331ca1d692b74a0509ddcc725622ed31a"

SRC_URI[GeoIP-dat.md5sum] = "d538e57ad9268fdc7955c6cf9a37c4a9"
SRC_URI[GeoIP-dat.sha256sum] = "b9c05eb8bfcf90a6ddfdc6815caf40a8db2710f0ce3dd48fbd6c24d485ae0449"

SRC_URI[GeoIPv6-dat.md5sum] = "a908d7a51c3883eb8f693ca197c7a142"
SRC_URI[GeoIPv6-dat.sha256sum] = "1b06e34eed2c539606a9ab4d97b2140ac80bf9f528d16ad2e3831c75014c2710"

SRC_URI[GeoLiteCity-dat.md5sum] = "d700c137232f8e077ac8db8577f699d9"
SRC_URI[GeoLiteCity-dat.sha256sum] = "90db2e52195e3d1bcdb2c2789209006d09de5c742812dbd9a1b36c12675ec4cd"

SRC_URI[GeoLiteCityv6-dat.md5sum] = "8ac3506ccabaae7e9f7d6301c2654313"
SRC_URI[GeoLiteCityv6-dat.sha256sum] = "1c5fced1657d09448b3ecc14b65c9536228a627d93ed4159d3438b2c6faa8506"

LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=243b725d71bb5df4a1e5920b344b86ad \
                    file://LICENSE;md5=0388276749a542b0d611601fa7c1dcc8 "

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--disable-static               \
                --disable-dependency-tracking  "

do_install() {
    make DESTDIR=${D} install
    install -d ${D}/${datadir}/GeoIP
    install ${WORKDIR}/GeoIP.dat.20181120 ${D}/${datadir}/GeoIP/GeoIP.dat
    install ${WORKDIR}/GeoIPv6.dat.20181120 ${D}/${datadir}/GeoIP/GeoIPv6.dat
    install ${WORKDIR}/GeoLiteCity.dat.20181120 ${D}/${datadir}/GeoIP/GeoLiteCity.dat
    install ${WORKDIR}/GeoLiteCityv6.dat.20181120 ${D}/${datadir}/GeoIP/GeoLiteCityv6.dat
    ln -s GeoLiteCity.dat ${D}${datadir}/GeoIP/GeoIPCity.dat
}

PACKAGES =+ "${PN}-database"
FILES_${PN}-database = ""
FILES_${PN}-database += "${datadir}/GeoIP/*"

# We cannot do much looking up without databases.
#
RDEPENDS_${PN} += "${PN}-database"

inherit ptest

do_configure_ptest() {
    sed -i -e "s/noinst_PROGRAMS = /test_PROGRAMS = /g" \
        -e 's:SRCDIR=\\"$(top_srcdir)\\":SRCDIR=\\"$(testdir)\\":' \
        ${S}/test/Makefile.am

    if ! grep "^testdir = " ${S}/test/Makefile.am ; then
        sed -e '/EXTRA_PROGRAMS = /itestdir = ${PTEST_PATH}/tests' \
            -i ${S}/test/Makefile.am
    fi

    sed -i -e "s:/usr/local/share:/usr/share:g" \
        ${S}/test/benchmark.c

    sed -i -e 's:"../data/:"/usr/share/GeoIP/:g' \
        ${S}/test/test-geoip-city.c \
        ${S}/test/test-geoip-isp.c \
        ${S}/test/test-geoip-asnum.c \
        ${S}/test/test-geoip-netspeed.c \
        ${S}/test/test-geoip-org.c \
        ${S}/test/test-geoip-region.c
}


do_install_ptest() {
    oe_runmake -C test DESTDIR=${D}  install-testPROGRAMS
    install ${S}/test/*.txt ${D}${PTEST_PATH}/tests
}
